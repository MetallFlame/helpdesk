package com.helpdeskonboot.helpdesk.service.impl;

import com.helpdeskonboot.helpdesk.dto.FeedbackDTO;
import com.helpdeskonboot.helpdesk.exception.FeedbackNotFoundException;
import com.helpdeskonboot.helpdesk.mapper.FeedbackMapper;
import com.helpdeskonboot.helpdesk.model.Feedback;
import com.helpdeskonboot.helpdesk.repository.FeedbackRepository;
import com.helpdeskonboot.helpdesk.service.FeedbackService;
import com.helpdeskonboot.helpdesk.service.TicketService;
import com.helpdeskonboot.helpdesk.service.UserService;
import com.helpdeskonboot.helpdesk.util.EmailUtil;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final TicketService ticketService;
    private final UserService userService;
    private final EmailUtil emailUtil;

    FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                        FeedbackMapper feedbackMapper,
                        TicketService ticketService,
                        UserService userService,
                        EmailUtil emailUtil) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.emailUtil = emailUtil;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @Override
    public FeedbackDTO getByTicketId(Long ticketId) {
        return feedbackMapper.toDTO(feedbackRepository.getByTicketId(ticketId).orElseThrow(FeedbackNotFoundException::new));
    }

    @Override
    public void createFeedback(FeedbackDTO feedbackDTO, Long ticketId, Long userId) {
        Feedback newFeedback = feedbackMapper.toEntity(feedbackDTO);
        newFeedback.setTicket(ticketService.getById(ticketId));
        newFeedback.setUser(userService.getById(userId));
        feedbackRepository.createFeedback(newFeedback);
        emailUtil.sendEmailForFeedback(newFeedback.getTicket().getAsignee().getId(), newFeedback.getTicket().getId());
    }
}
