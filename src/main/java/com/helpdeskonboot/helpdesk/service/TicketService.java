package com.helpdeskonboot.helpdesk.service;

import com.helpdeskonboot.helpdesk.dto.TicketDTO;
import com.helpdeskonboot.helpdesk.model.Ticket;
import com.helpdeskonboot.helpdesk.service.impl.ticket.UserAction;

import java.util.List;

public interface TicketService {
    Ticket getById(Long id);

    TicketDTO getDTOById(Long id);

    Long createTicket(TicketDTO ticketDTO, Long userId, String desiredDate);

    void editTicket(TicketDTO editedTicket, Long userId, String desiredDate);

    void changeTicketState(Long ticketId, Long userId, UserAction action);

    List<TicketDTO> getAllByChoise(Long userId, String choise);

}
