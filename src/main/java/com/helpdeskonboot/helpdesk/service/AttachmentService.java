package com.helpdeskonboot.helpdesk.service;

import com.helpdeskonboot.helpdesk.dto.AttachmentDTO;
import com.helpdeskonboot.helpdesk.model.Attachment;

import java.util.List;

public interface AttachmentService {
    void createAttachment(byte[] content, String fileName, Long ticketId, Long userId);

    List<AttachmentDTO> getAllByTicketId(Long ticketId);

    byte[] getContentByAttachmentId(Long attachmentId);

    void deleteAttachment(Long attachmentId, Long ticketId, Long userId);

    Attachment getAttachmentById(Long attachmentId);

}
