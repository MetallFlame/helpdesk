package com.helpdeskonboot.helpdesk.controller;

import com.helpdeskonboot.helpdesk.dto.FeedbackDTO;
import com.helpdeskonboot.helpdesk.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("")
    public ResponseEntity<Map> createFeedback(@RequestParam Long ticketId,
                                              @RequestParam Long userId,
                                              @RequestBody FeedbackDTO newFeedback) {
        feedbackService.createFeedback(newFeedback, ticketId, userId);
        return ResponseEntity.ok(Collections.singletonMap("Response", "Feedback created"));
    }

    @GetMapping("")
    public ResponseEntity<FeedbackDTO> getFeedback(@RequestParam Long ticketId) {
        return ResponseEntity.ok(feedbackService.getByTicketId(ticketId));
    }

}
