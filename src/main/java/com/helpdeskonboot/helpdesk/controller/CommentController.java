package com.helpdeskonboot.helpdesk.controller;

import com.helpdeskonboot.helpdesk.dto.CommentDTO;
import com.helpdeskonboot.helpdesk.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public ResponseEntity<List<CommentDTO>> getCommentsByTicketId(@RequestParam Long ticketId) {
        return ResponseEntity.ok(commentService.getByTicketId(ticketId));
    }

    @PostMapping("")
    public ResponseEntity<Map> createComment(@RequestParam Long ticketId, @RequestBody CommentDTO commentDTO) {
        commentService.createComment(ticketId, commentDTO.getUserId(), commentDTO.getText());
        return ResponseEntity.ok(Collections.singletonMap("Response", "Comment created"));
    }
}
