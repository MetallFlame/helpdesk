package com.helpdeskonboot.helpdesk.repository;


import com.helpdeskonboot.helpdesk.model.Feedback;

import java.util.Optional;

public interface FeedbackRepository {
    void createFeedback(Feedback newFreedback);

    Optional<Feedback> getByTicketId(Long ticketId);

    Optional<Feedback> getById(Long feedbackId);
}
