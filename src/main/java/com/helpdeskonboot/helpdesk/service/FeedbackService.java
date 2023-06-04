package com.helpdeskonboot.helpdesk.service;

import com.helpdeskonboot.helpdesk.dto.FeedbackDTO;

public interface FeedbackService {
    FeedbackDTO getByTicketId(Long ticketId);

    void createFeedback(FeedbackDTO feedbackDTO, Long ticketId, Long userId);
}
