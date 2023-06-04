package com.helpdeskonboot.helpdesk.repository.impl;

import com.helpdeskonboot.helpdesk.model.Comment;
import com.helpdeskonboot.helpdesk.repository.CommentRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> getByTicketId(Long ticketId) {
        return entityManager.createQuery("From Comment where major_ticket_id =: ticketId", Comment.class)
                .setParameter("ticketId", ticketId)
                .getResultList();
    }

    @Override
    public void createComment(Comment newComment) {
        entityManager.persist(newComment);
    }
}
