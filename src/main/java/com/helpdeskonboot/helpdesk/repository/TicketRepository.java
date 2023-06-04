package com.helpdeskonboot.helpdesk.repository;

import com.helpdeskonboot.helpdesk.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> getById(Long id);

    List<Ticket> getByOwnerId(Long userId);

    void createTicket(Ticket ticket);

    List<Ticket> getTicketsApprovedByUser(Long userId);

    List<Ticket> getTicketsAssignedByUser(Long userId);

    List<Ticket> getAllApproved();

    List<Ticket> getAllNewFromEmployee();

    List<Ticket> getAllRelevantToManager(Long userId);

    void updateTicket(Ticket ticket);

}
