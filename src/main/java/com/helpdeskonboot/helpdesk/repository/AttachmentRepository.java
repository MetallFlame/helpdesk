package com.helpdeskonboot.helpdesk.repository;

import com.helpdeskonboot.helpdesk.model.Attachment;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository {
    void createAttachment(Attachment attachment);

    List<Attachment> getAllByTicketId(Long ticketId);

    void deleteAttachment(Long attachmentId);

    Optional<Attachment> getAttachmentById(Long attachmentId);
}
