package com.helpdeskonboot.helpdesk.service.impl.ticket;


import com.helpdeskonboot.helpdesk.model.State;
import com.helpdeskonboot.helpdesk.model.Ticket;
import com.helpdeskonboot.helpdesk.model.User;
import com.helpdeskonboot.helpdesk.model.UserRole;
import com.helpdeskonboot.helpdesk.service.HistoryService;
import com.helpdeskonboot.helpdesk.util.EmailUtil;

public enum UserAction {

    APPROVE {
        @Override
        protected void execute(Ticket ticketForAction, User actionUser, EmailUtil emailUtil, HistoryService historyService) {
            ticketForAction.setApprover(actionUser);
            emailUtil.sendEmailForApprovedTicket(ticketForAction.getId(), ticketForAction.getOwner().getId());
            historyService.addHistoryForChangedState(ticketForAction,
                    actionUser.getId(),
                    ticketForAction.getState().getStateValue(),
                    State.APPROVED.getStateValue());
            ticketForAction.setState(State.APPROVED);
        }

    },

    ASIGN {
        @Override
        public void execute(Ticket ticketForAction, User actionUser, EmailUtil emailUtil, HistoryService historyService) {
            ticketForAction.setAsignee(actionUser);
            historyService.addHistoryForChangedState(ticketForAction,
                    actionUser.getId(),
                    ticketForAction.getState().getStateValue(),
                    State.IN_PROGRESS.getStateValue());
            ticketForAction.setState(State.IN_PROGRESS);
        }

    },

    DONE {
        @Override
        protected void execute(Ticket ticketForAction, User actionUser, EmailUtil emailUtil, HistoryService historyService) {
            emailUtil.sendEmailForDoneTicket(ticketForAction.getId(), ticketForAction.getOwner().getId());
            historyService.addHistoryForChangedState(ticketForAction,
                    actionUser.getId(),
                    ticketForAction.getState().getStateValue(),
                    State.DONE.getStateValue());
            ticketForAction.setState(State.DONE);
        }

    },

    SUBMIT {
        @Override
        protected void execute(Ticket ticketForAction, User actionUser, EmailUtil emailUtil, HistoryService historyService) {
            emailUtil.sendEmailForNewTicket(ticketForAction.getId());
            historyService.addHistoryForChangedState(ticketForAction,
                    actionUser.getId(),
                    ticketForAction.getState().getStateValue(),
                    State.NEW.getStateValue());
            ticketForAction.setState(State.NEW);
        }

    },

    CANCEL {
        @Override
        protected void execute(Ticket ticketForAction, User actionUser, EmailUtil emailUtil, HistoryService historyService) {
            historyService.addHistoryForChangedState(ticketForAction,
                    actionUser.getId(),
                    ticketForAction.getState().getStateValue(),
                    State.CANCELLED.getStateValue());
            if (!actionUser.getId().equals(ticketForAction.getOwner().getId())) {
                if (actionUser.getRole().equals(UserRole.MANAGER)) {
                    ticketForAction.setApprover(actionUser);
                    emailUtil.sendEmailForCancelTicketByManager(ticketForAction.getId(), ticketForAction.getOwner().getId());
                } else if (actionUser.getRole().equals(UserRole.ENGINEER)) {
                    ticketForAction.setAsignee(actionUser);
                    emailUtil.sendEmailForCancelTicketByEngineer(ticketForAction.getId(), ticketForAction.getOwner().getId());
                }
            }
            ticketForAction.setState(State.CANCELLED);
        }
    },

    DECLINE {
        @Override
        protected void execute(Ticket ticketForAction, User actionUser, EmailUtil emailUtil, HistoryService historyService) {
            historyService.addHistoryForChangedState(ticketForAction,
                    actionUser.getId(),
                    ticketForAction.getState().getStateValue(),
                    State.DECLINED.getStateValue());
            if (!actionUser.getId().equals(ticketForAction.getOwner().getId())) {
                ticketForAction.setApprover(actionUser);
            }
            emailUtil.sendEmailForDeclineTicket(ticketForAction.getId(), ticketForAction.getOwner().getId());
            ticketForAction.setState(State.DECLINED);
        }

    };

    protected abstract void execute(Ticket ticketForAction, User actionUser, EmailUtil emailUtil, HistoryService historyService);



}