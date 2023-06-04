package com.helpdeskonboot.helpdesk.service.impl;


import com.helpdeskonboot.helpdesk.dto.HistoryDTO;
import com.helpdeskonboot.helpdesk.mapper.HistoryMapper;
import com.helpdeskonboot.helpdesk.model.History;
import com.helpdeskonboot.helpdesk.model.Ticket;
import com.helpdeskonboot.helpdesk.repository.HistoryRepository;
import com.helpdeskonboot.helpdesk.service.HistoryService;
import com.helpdeskonboot.helpdesk.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class HistoryServiceImpl implements HistoryService {

    private static final String DOT = ".";
    private static final String COLON_WITH_SPACE = ": ";
    private static final String FROM = " from ";
    private static final String TO = " to ";
    private static final String FILE_ATTACHED = "File is attached";
    private static final String FILE_REMOVED = "File is removed";
    private static final String TICKET_CREATED = "Ticket is created";
    private static final String TICKET_STATUS_CHANGED = "Ticket status is changed";
    private static final String TICKET_EDITED = "Ticket is edited";
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;
    private final UserService userService;

    public HistoryServiceImpl(HistoryRepository historyRepository, HistoryMapper historyMapper, UserService userService) {
        this.historyRepository = historyRepository;
        this.historyMapper = historyMapper;
        this.userService = userService;
    }

    @Override
    public List<HistoryDTO> getAllByTicketId(Long ticketId) {
        return historyMapper.toDTOList(historyRepository.getByTicketId(ticketId));
    }

    @Override
    public void addHistoryForAttachedFile(Ticket ticket, Long userId, String fileName) {
        addNewHistory(ticket, userId, FILE_ATTACHED + DOT, FILE_ATTACHED + COLON_WITH_SPACE + fileName);
    }

    @Override
    public void addHistoryForRemovedFile(Ticket ticket, Long userId, String fileName) {
        addNewHistory(ticket, userId, FILE_REMOVED + DOT, FILE_REMOVED + COLON_WITH_SPACE + fileName);
    }

    @Override
    public void addHistoryForCreatedTicket(Ticket ticket, Long userId) {
        addNewHistory(ticket, userId, TICKET_CREATED + DOT, TICKET_CREATED + DOT);
    }

    @Override
    public void addHistoryForChangedState(Ticket ticket, Long userId, String oldState, String newState) {
        addNewHistory(ticket, userId, TICKET_STATUS_CHANGED + DOT, TICKET_STATUS_CHANGED + FROM + oldState + TO + newState);
    }

    @Override
    public void addHistoryForEditedTicket(Ticket ticket, Long userId) {
        addNewHistory(ticket, userId, TICKET_EDITED + DOT, TICKET_EDITED + DOT);
    }


    private void addNewHistory(Ticket ticket, Long userId, String action, String description) {
        History historyString = new History();
        historyString.setAction(action);
        historyString.setDescription(description);
        historyString.setDate(LocalDateTime.now());
        historyString.setTicket(ticket);
        historyString.setUser(userService.getById(userId));
        historyRepository.addHistory(historyString);
    }
}
