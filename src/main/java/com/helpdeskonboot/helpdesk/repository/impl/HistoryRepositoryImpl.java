package com.helpdeskonboot.helpdesk.repository.impl;


import com.helpdeskonboot.helpdesk.model.History;
import com.helpdeskonboot.helpdesk.repository.HistoryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List getByTicketId(Long ticketId) {
        return entityManager.createQuery("From History where action_ticket_id =: ticketId")
                .setParameter("ticketId", ticketId)
                .getResultList();
    }

    @Override
    public void addHistory(History historyCase) {
        entityManager.persist(historyCase);
    }
}
