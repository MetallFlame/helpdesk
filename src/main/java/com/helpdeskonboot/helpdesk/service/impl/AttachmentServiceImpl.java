package com.helpdeskonboot.helpdesk.service.impl;


import com.helpdeskonboot.helpdesk.dto.AttachmentDTO;
import com.helpdeskonboot.helpdesk.exception.AttachmentNotFoundException;
import com.helpdeskonboot.helpdesk.mapper.AttachmentMapper;
import com.helpdeskonboot.helpdesk.model.Attachment;
import com.helpdeskonboot.helpdesk.model.Ticket;
import com.helpdeskonboot.helpdesk.repository.AttachmentRepository;
import com.helpdeskonboot.helpdesk.service.AttachmentService;
import com.helpdeskonboot.helpdesk.service.HistoryService;
import com.helpdeskonboot.helpdesk.service.TicketService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;
    private final HistoryService historyService;
    private final TicketService ticketService;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository,
                                 AttachmentMapper attachmentMapper,
                                 HistoryService historyService,
                                 TicketService ticketService) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentMapper = attachmentMapper;
        this.historyService = historyService;
        this.ticketService = ticketService;

    }

    @Override
    public void createAttachment(byte[] content, String fileName, Long ticketId, Long userId) {
        Ticket ticketToAddAttachment = ticketService.getById(ticketId);
        Attachment newAttachment = new Attachment();
        newAttachment.setContent(content);
        newAttachment.setName(fileName);
        newAttachment.setTicket(ticketToAddAttachment);
        attachmentRepository.createAttachment(newAttachment);
        this.historyService.addHistoryForAttachedFile(ticketToAddAttachment, userId, fileName);
    }

    @Override
    public List<AttachmentDTO> getAllByTicketId(Long ticketId) {
        return attachmentMapper.toDTOList(attachmentRepository.getAllByTicketId(ticketId));
    }

    @Override
    public byte[] getContentByAttachmentId(Long attachmentId) {
        return getAttachmentById(attachmentId).getContent();
    }

    @Override
    public void deleteAttachment(Long attachmentId, Long ticketId, Long userId) {
        Ticket ticketToDeleteAttachment = ticketService.getById(ticketId);
        Attachment file = getAttachmentById(attachmentId);
        attachmentRepository.deleteAttachment(attachmentId);
        this.historyService.addHistoryForRemovedFile(ticketToDeleteAttachment, userId, file.getName());


    }

    @Override
    public Attachment getAttachmentById(Long attachmentId) {
        return attachmentRepository.getAttachmentById(attachmentId).orElseThrow(AttachmentNotFoundException::new);
    }
}
