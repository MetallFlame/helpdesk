package com.helpdeskonboot.helpdesk.util;

import com.helpdeskonboot.helpdesk.dto.UserDTO;
import com.helpdeskonboot.helpdesk.exception.UserNotFoundException;
import com.helpdeskonboot.helpdesk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@PropertySource(value = "classpath:application.properties")
@Component
public class EmailUtil {

    private final static String DEAR = "Dear ";
    private final static String TAG_PART = "'>";
    private final static String TAG_A_CLOSED = "</a>";
    private final static String DOT = ".";
    private final static String FEEDBACK_PROVIDED_EMAIL_SUBJECT = "Feedback was provided";
    private final static String FEEDBACK_PROVIDED_TEXT = ", The feedback was provided on ticket <a href='http://localhost:4200/overview?id=";
    private final static String TICKET_CREATED_EMAIL_SUBJECT = "New ticket for approval";
    private final static String TICKET_CREATED_TEXT_BEGIN = "Managers, New ticket <a href='http://localhost:4200/overview?id=";
    private final static String TICKET_CREATED_TEXT_END = "</a> is waiting for your approval.";
    private final static String TICKET_APPROVED_EMAIL_SUBJECT = "Ticket was approved";
    private final static String TICKET_APPROVED_TEXT_BEGIN = "Users, Ticket <a href='http://localhost:4200/overview?id=";
    private final static String TICKET_APPROVED_TEXT_END = "</a> was approved by the Manager.";
    private final static String TICKET_DONE_EMAIL_SUBJECT = "Ticket was done";
    private final static String TICKET_DONE_TEXT_BEGIN = ", Ticket <a href='http://localhost:4200/overview?id=";
    private final static String TICKET_DONE_TEXT_END = "</a> was done by the Engineer.";
    private final static String TICKET_CANCEL_EMAIL_SUBJECT = "Ticket was cancelled";
    private final static String TICKET_CANCEL_TEXT_BEGIN = ", Ticket <a href='http://localhost:4200/overview?id=";
    private final static String TICKET_CANCEL_TEXT_END_BY_MANAGER = "</a> was cancelled by the Manager.";
    private final static String TICKET_CANCEL_TEXT_END_BY_ENGINEER = "</a> was cancelled by the Engineer.";
    private final static String TICKET_DECLINE_EMAIL_SUBJECT = "Ticket was declined";
    private final static String TICKET_DECLINE_TEXT_BEGIN = ", Ticket " + "<a href='http://localhost:4200/overview?id=";
    private final static String TICKET_DECLINE_TEXT_END = "</a> was declined by the Manager.";
    private final boolean flag = false;

    @Autowired
    @Qualifier("gmailSession")
    private Session session;

    private UserService userService;

    public EmailUtil(UserService userService) {
        this.userService = userService;
    }

    private void sendEmail(String to, String subject, String text) {
        if (flag) {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("helpdesknotificator@gmail.com"));
                message.addRecipients(Message.RecipientType.TO, to);
                message.setSubject(subject);
                message.setContent(text, "text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }

    }

    public void sendEmailForFeedback(Long asigneeId, Long ticketId) throws UserNotFoundException {
        UserDTO asignee = userService.getDTOById(asigneeId);
        sendEmail(asignee.getEmail(), FEEDBACK_PROVIDED_EMAIL_SUBJECT, DEAR
                + asignee.getFirstName()
                + " "
                + asignee.getLastName()
                + FEEDBACK_PROVIDED_TEXT
                + ticketId
                + TAG_PART
                + ticketId
                + TAG_A_CLOSED
                + DOT);
    }

    public void sendEmailForNewTicket(Long ticketId) {
        sendEmail(userService.getAllManagersEmails(), TICKET_CREATED_EMAIL_SUBJECT, DEAR
                + TICKET_CREATED_TEXT_BEGIN
                + ticketId
                + TAG_PART
                + ticketId
                + TICKET_CREATED_TEXT_END);
    }

    public void sendEmailForApprovedTicket(Long ticketId, Long userCreatorId) throws UserNotFoundException {
        sendEmail(userService.getDTOById(userCreatorId).getEmail() + ", "
                + userService.getAllEngineersEmails(), TICKET_APPROVED_EMAIL_SUBJECT, DEAR
                + TICKET_APPROVED_TEXT_BEGIN
                + ticketId
                + TAG_PART
                + ticketId
                + TICKET_APPROVED_TEXT_END);
    }

    public void sendEmailForDoneTicket(Long ticketId, Long userOwnerId) throws UserNotFoundException {
        UserDTO userOwnerDTO = userService.getDTOById(userOwnerId);
        sendEmail(userOwnerDTO.getEmail(), TICKET_DONE_EMAIL_SUBJECT, DEAR + userOwnerDTO.getFirstName() + " "
                + userOwnerDTO.getLastName()
                + TICKET_DONE_TEXT_BEGIN
                + ticketId
                + TAG_PART
                + ticketId
                + TICKET_DONE_TEXT_END);
    }

    public void sendEmailForCancelTicketByManager(Long ticketId, Long userOwnerId) throws UserNotFoundException {
        sendEmailForCancelTicket(ticketId, userOwnerId, TICKET_CANCEL_TEXT_END_BY_MANAGER);
    }

    public void sendEmailForCancelTicketByEngineer(Long ticketId, Long userOwnerId) throws UserNotFoundException {
        sendEmailForCancelTicket(ticketId, userOwnerId, TICKET_CANCEL_TEXT_END_BY_ENGINEER);
    }

    private void sendEmailForCancelTicket(Long ticketId,
                                          Long userOwnerId,
                                          String endString) throws UserNotFoundException {
        UserDTO userOwnerDTO = userService.getDTOById(userOwnerId);
        sendEmail(userOwnerDTO.getEmail(), TICKET_CANCEL_EMAIL_SUBJECT, DEAR + userOwnerDTO.getFirstName() + " "
                + userOwnerDTO.getLastName()
                + TICKET_CANCEL_TEXT_BEGIN
                + ticketId
                + TAG_PART
                + ticketId
                + endString);
    }

    public void sendEmailForDeclineTicket(Long ticketId, Long userOwnerId) throws UserNotFoundException {
        UserDTO userOwnerDTO = userService.getDTOById(userOwnerId);
        sendEmail(userOwnerDTO.getEmail(), TICKET_DECLINE_EMAIL_SUBJECT, DEAR
                + userOwnerDTO.getFirstName() + " " + userOwnerDTO.getLastName()
                + TICKET_DECLINE_TEXT_BEGIN
                + ticketId
                + TAG_PART
                + ticketId
                + TICKET_DECLINE_TEXT_END);
    }
}
