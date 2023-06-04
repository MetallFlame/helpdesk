package com.helpdeskonboot.helpdesk.repository;

import com.helpdeskonboot.helpdesk.model.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> getByTicketId(Long ticketId);

    void createComment(Comment newComment);
}
