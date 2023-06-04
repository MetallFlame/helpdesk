package com.helpdeskonboot.helpdesk.repository.impl;


import com.helpdeskonboot.helpdesk.model.Attachment;
import com.helpdeskonboot.helpdesk.repository.AttachmentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class AttachmentRepositoryImpl implements AttachmentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createAttachment(Attachment attachment) {
        entityManager.persist(attachment);
    }

    @Override
    public List<Attachment> getAllByTicketId(Long ticketId) {
        return entityManager.createQuery("From Attachment where attached_ticket_id =: ticketId", Attachment.class)
                .setParameter("ticketId", ticketId)
                .getResultList();
    }

    @Override
    public void deleteAttachment(Long attachmentId) {
        try {
            entityManager.remove(entityManager.find(Attachment.class, attachmentId));
            entityManager.flush();
        } catch (Exception ex) {
            entityManager.flush();
        }
    }

    @Override
    public Optional<Attachment> getAttachmentById(Long attachmentId) {
        return Optional.ofNullable(entityManager.find(Attachment.class, attachmentId));
    }
}
