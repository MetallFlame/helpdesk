package com.helpdeskonboot.helpdesk.controller;

import com.helpdeskonboot.helpdesk.dto.HistoryDTO;
import com.helpdeskonboot.helpdesk.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/histories")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("")
    public ResponseEntity<List<HistoryDTO>> getAllHistoryByTicketId(@RequestParam Long ticketId) {
        return ResponseEntity.ok(historyService.getAllByTicketId(ticketId));
    }
}
