package com.helpdeskonboot.helpdesk.controller;

import com.helpdeskonboot.helpdesk.dto.TicketDTO;
import com.helpdeskonboot.helpdesk.model.Urgency;
import com.helpdeskonboot.helpdesk.service.TicketService;
import com.helpdeskonboot.helpdesk.service.impl.ticket.UserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/urgencies")
    public ResponseEntity<Urgency[]> getAllUrgencyValues() {
        return ResponseEntity.ok(Urgency.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getDTOById(@PathVariable Long id) {
        logger.info("ticket by id test");
        return ResponseEntity.ok(ticketService.getDTOById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<TicketDTO>> getAllByChoise(@RequestParam Long userId, @RequestParam String selection) {
        return ResponseEntity.ok(ticketService.getAllByChoise(userId, selection));
    }

    @PostMapping("")
    public ResponseEntity<Map> createTicket(@RequestBody TicketDTO newTicket,
                                            @RequestParam Long userId,
                                            @RequestParam String desiredDate) {
        return ResponseEntity.ok(Collections.singletonMap("ticketId", ticketService.createTicket(newTicket, userId, desiredDate)));
    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, String>> editTicket(@RequestBody TicketDTO editedTicket,
                                          @RequestParam Long userId,
                                          @RequestParam String desiredDate) {
        ticketService.editTicket(editedTicket, userId, desiredDate);
        return ResponseEntity.ok(Collections.singletonMap("Response", "Ticket edited"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> changeTicketState(@PathVariable Long id,
                                                 @RequestParam Long userId,
                                                 @RequestParam UserAction action) {
        ticketService.changeTicketState(id, userId, action);
        return ResponseEntity.ok(Collections.singletonMap("Response", "Ticket state changed"));
    }
}
