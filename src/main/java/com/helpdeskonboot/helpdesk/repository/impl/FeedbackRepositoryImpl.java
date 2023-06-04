package com.helpdeskonboot.helpdesk.repository.impl;


import com.helpdeskonboot.helpdesk.model.Feedback;
import com.helpdeskonboot.helpdesk.repository.FeedbackRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createFeedback(Feedback newFeedback) {
        entityManager.persist(newFeedback);
    }

    @Override
    public Optional<Feedback> getByTicketId(Long ticketId) {
        try {
            return Optional.ofNullable(entityManager
                    .createQuery("From Feedback where feedbacked_ticket_id =: ticketId", Feedback.class)
                    .setParameter("ticketId", ticketId)
                    .getSingleResult());
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Feedback> getById(Long feedbackId) {
        return Optional.ofNullable(entityManager.find(Feedback.class, feedbackId));
    }


}
