package com.helpdeskonboot.helpdesk.service.impl.ticket;

import com.helpdeskonboot.helpdesk.dto.TicketDTO;
import com.helpdeskonboot.helpdesk.exception.TicketNotFoundException;
import com.helpdeskonboot.helpdesk.exception.WrongTicketChoiseEcxeption;
import com.helpdeskonboot.helpdesk.mapper.TicketMapper;
import com.helpdeskonboot.helpdesk.model.State;
import com.helpdeskonboot.helpdesk.model.Ticket;
import com.helpdeskonboot.helpdesk.model.User;
import com.helpdeskonboot.helpdesk.model.UserRole;
import com.helpdeskonboot.helpdesk.repository.TicketRepository;
import com.helpdeskonboot.helpdesk.service.CategoryService;
import com.helpdeskonboot.helpdesk.service.HistoryService;
import com.helpdeskonboot.helpdesk.service.TicketService;

import com.helpdeskonboot.helpdesk.service.UserService;
import com.helpdeskonboot.helpdesk.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final CategoryService categoryService;
    private final HistoryService historyService;
    private final EmailUtil emailUtil;
    private final UserService userService;
    private final DateTimeFormatter dateTimeFormatter;
    private final String zeroTime = " 00:00";

    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketMapper ticketMapper,
                             CategoryService categoryService,
                             UserService userService,
                             HistoryService historyService,
                             EmailUtil emailUtil) {

        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.categoryService = categoryService;
        this.historyService = historyService;
        this.emailUtil = emailUtil;
        this.userService = userService;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    private List<TicketDTO> getAllRelevantToUser(Long userId) {
        UserRole userRole = userService.getById(userId).getRole();
        if (userRole.equals(UserRole.MANAGER)) {
            return ticketMapper.toDTOList(ticketRepository.getAllRelevantToManager(userId));
        }
        if (userRole.equals(UserRole.ENGINEER)) {
            return ticketMapper.toDTOList(ticketRepository.getAllApproved());
        }
        return new ArrayList<>();
    }

    private List<TicketDTO> getOwnForUser(Long userId) {
        UserRole userRole = this.userService.getById(userId).getRole();
        if ((userRole.equals(UserRole.MANAGER)) || (userRole.equals(UserRole.EMPLOYEE))) {
            return ticketMapper.toDTOList(ticketRepository.getByOwnerId(userId));
        } else {
            return ticketMapper.toDTOList(ticketRepository.getTicketsAssignedByUser(userId));
        }
    }

    @Override
    public TicketDTO getDTOById(Long id) {
        return ticketMapper.toDTOWithUsers(getById(id));
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.getById(id).orElseThrow(TicketNotFoundException::new);
    }

    @Override
    public Long createTicket(TicketDTO ticketDTO, Long userId, String desiredDate) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticket.setDesiredResolutionDate(LocalDateTime.parse(desiredDate + zeroTime, dateTimeFormatter));
        ticket.setCreatedOn(LocalDateTime.now());
        ticket.setCategory(categoryService.getByName(ticketDTO.getCategory()));
        ticket.setOwner(userService.getById(userId));
        ticketRepository.createTicket(ticket);
        historyService.addHistoryForCreatedTicket(ticket, userId);
        if (ticket.getState().equals(State.NEW)) {
            emailUtil.sendEmailForNewTicket(ticket.getId());
        }
        return ticket.getId();
    }

    @Override
    public void editTicket(TicketDTO editedTicket, Long userId, String desiredDate) {
        Ticket ticketFromDB = getById(editedTicket.getId());
        ticketFromDB.setName(editedTicket.getName());
        ticketFromDB.setDescription(editedTicket.getDescription());
        ticketFromDB.setDesiredResolutionDate(LocalDateTime.parse(desiredDate + zeroTime, dateTimeFormatter));
        ticketFromDB.setUrgency(editedTicket.getUrgency());
        ticketFromDB.setCategory(categoryService.getByName(editedTicket.getCategory()));
        historyService.addHistoryForEditedTicket(ticketFromDB, userId);
    }

    @Override
    public void changeTicketState(Long ticketId, Long userId, UserAction action) {
        Ticket ticketForAction = getById(ticketId);
        User actionUser = userService.getById(userId);
        action.execute(ticketForAction, actionUser, emailUtil, historyService);
    }

    @Override
    public List<TicketDTO> getAllByChoise(Long userId, String choise) {
        if (choise.equals("relevant")) {
            return getAllRelevantToUser(userId);
        }
        if (choise.equals("own")) {
            return getOwnForUser(userId);
        }
        throw new WrongTicketChoiseEcxeption();
    }
}
