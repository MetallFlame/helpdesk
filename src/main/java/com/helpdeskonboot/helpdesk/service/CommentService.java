package com.helpdeskonboot.helpdesk.service;

import com.helpdeskonboot.helpdesk.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getByTicketId(Long ticketId);

    void createComment(Long ticketId, Long userId, String commentText);
}
