package com.helpdeskonboot.helpdesk.controller;

import com.helpdeskonboot.helpdesk.dto.AttachmentDTO;
import com.helpdeskonboot.helpdesk.service.AttachmentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/attachments")
public class AttachmentController {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("")
    public ResponseEntity<List<AttachmentDTO>> getAttachmentDTOListByTicketId(@RequestParam Long ticketId) {
        return ResponseEntity.ok(attachmentService.getAllByTicketId(ticketId));
    }

    @PostMapping(path = "", consumes = "multipart/form-data")
    public ResponseEntity<Map> uploadAttachment(@NotNull @RequestParam("attachment") MultipartFile multipartFile,
                                                @NotNull @RequestParam Long ticketId,
                                                @NotNull @RequestParam Long userId) throws IOException {
        attachmentService.createAttachment(multipartFile.getBytes(),
                multipartFile.getOriginalFilename(), ticketId, userId);
        return ResponseEntity.ok(Collections.singletonMap("Response", "Attachment created"));
    }

    @GetMapping("/{attachmentId}")
    public ByteArrayResource downloadAttachment(@NotNull @PathVariable Long attachmentId) {
        byte[] attachment = attachmentService.getContentByAttachmentId(attachmentId);
        return new ByteArrayResource(attachment);
    }

    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<Map> deleteAttachment(@NotNull @PathVariable Long attachmentId,
                                                @NotNull @RequestParam Long ticketId,
                                                @NotNull @RequestParam Long userId) {
        attachmentService.deleteAttachment(attachmentId, ticketId, userId);
        return ResponseEntity.ok(Collections.singletonMap("Response", "Attachment deleted"));
    }
}
