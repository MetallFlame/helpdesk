package com.helpdeskonboot.helpdesk.service.impl;

import com.helpdeskonboot.helpdesk.dto.CommentDTO;
import com.helpdeskonboot.helpdesk.mapper.CommentMapper;
import com.helpdeskonboot.helpdesk.model.Comment;
import com.helpdeskonboot.helpdesk.repository.CommentRepository;
import com.helpdeskonboot.helpdesk.service.CommentService;
import com.helpdeskonboot.helpdesk.service.TicketService;
import com.helpdeskonboot.helpdesk.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TicketService ticketService;
    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentRepository commentRepository,
                              CommentMapper commentMapper,
                              UserService userService,
                              TicketService ticketService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @Override
    public List<CommentDTO> getByTicketId(Long ticketId) {
        return commentMapper.toDTOList(commentRepository.getByTicketId(ticketId));
    }

    @Override
    public void createComment(Long ticketId, Long userId, String commentText) {
        Comment newComment = new Comment();
        newComment.setUser(userService.getById(userId));
        newComment.setDate(LocalDateTime.now());
        newComment.setTicket(ticketService.getById(ticketId));
        newComment.setText(commentText);
        commentRepository.createComment(newComment);
    }
}
