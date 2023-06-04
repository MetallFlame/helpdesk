package com.helpdeskonboot.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdeskonboot.helpdesk.model.State;
import com.helpdeskonboot.helpdesk.model.Urgency;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TicketDTO {


    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime desiredResolutionDate;

    @NotNull
    private State state;

    @NotNull
    private String category;

    @NotNull
    private Urgency urgency;

    private String asigneeString;
    private String approverString;
    private String ownerString;

    public TicketDTO() {
    }

    public String getAsigneeString() {
        return asigneeString;
    }

    public void setAsigneeString(String asigneeString) {
        this.asigneeString = asigneeString;
    }

    public String getApproverString() {
        return approverString;
    }

    public void setApproverString(String approverString) {
        this.approverString = approverString;
    }

    public String getOwnerString() {
        return ownerString;
    }

    public void setOwnerString(String ownerString) {
        this.ownerString = ownerString;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(LocalDateTime desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
