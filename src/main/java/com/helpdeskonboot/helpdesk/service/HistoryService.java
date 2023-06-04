package com.helpdeskonboot.helpdesk.service;

import com.helpdeskonboot.helpdesk.dto.HistoryDTO;
import com.helpdeskonboot.helpdesk.model.Ticket;

import java.util.List;

public interface HistoryService {
    List<HistoryDTO> getAllByTicketId(Long ticketId);

    void addHistoryForAttachedFile(Ticket ticket, Long userId, String fileName);

    void addHistoryForRemovedFile(Ticket ticket, Long userId, String fileName);

    void addHistoryForCreatedTicket(Ticket ticket, Long userId);

    void addHistoryForChangedState(Ticket ticket, Long userId, String oldState, String newState);

    void addHistoryForEditedTicket(Ticket ticket, Long userId);
}
