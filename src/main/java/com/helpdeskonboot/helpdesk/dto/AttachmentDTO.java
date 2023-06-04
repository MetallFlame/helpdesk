package com.helpdeskonboot.helpdesk.dto;

import javax.validation.constraints.NotNull;

public class AttachmentDTO {

    private Long id;
    private String name;
    private byte[] content;

    public AttachmentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
