package com.helpdeskonboot.helpdesk.repository.impl;


import com.helpdeskonboot.helpdesk.model.State;
import com.helpdeskonboot.helpdesk.model.Ticket;
import com.helpdeskonboot.helpdesk.model.UserRole;
import com.helpdeskonboot.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Ticket> getById(Long ticketId) {
        return Optional.ofNullable(entityManager.find(Ticket.class, ticketId));
    }

    @Override
    public List<Ticket> getByOwnerId(Long userId) {
        return entityManager.createQuery("from Ticket where owner_id = :user_id", Ticket.class)
                .setParameter("user_id", userId).getResultList();
    }

    @Override
    public void createTicket(Ticket ticket) {
        entityManager.persist(ticket);
    }

    @Override
    public List<Ticket> getTicketsApprovedByUser(Long userId) {
        return entityManager.createQuery("From Ticket where approver_id =: approver_id", Ticket.class)
                .setParameter("approver_id", userId)
                .getResultList();
    }

    @Override
    public List<Ticket> getTicketsAssignedByUser(Long userId) {
        return entityManager.createQuery("From Ticket where asignee_id =: asignee_id", Ticket.class)
                .setParameter("asignee_id", userId)
                .getResultList();
    }

    @Override
    public List<Ticket> getAllApproved() {
        return entityManager.createQuery("From Ticket where state =: state", Ticket.class)
                .setParameter("state", State.APPROVED)
                .getResultList();
    }

    @Override
    public List<Ticket> getAllNewFromEmployee() {
        return entityManager.createQuery("select t From Ticket t where " +
                "t.state =: state and t.owner.role =: role", Ticket.class)
                .setParameter("state", State.NEW)
                .setParameter("role", UserRole.EMPLOYEE)
                .getResultList();
    }

    @Override
    public List<Ticket> getAllRelevantToManager(Long userId) {
        return entityManager.createQuery("From Ticket where " +
                "(state =: state and owner.role =: role) or" +
                " approver_id =: userId", Ticket.class)
                .setParameter("state", State.NEW)
                .setParameter("role", UserRole.EMPLOYEE)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        entityManager.merge(ticket);
    }
}
