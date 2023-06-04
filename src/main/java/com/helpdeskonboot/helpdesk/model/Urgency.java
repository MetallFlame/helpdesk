package com.helpdeskonboot.helpdesk.model;

public enum Urgency {
    CRITICAL("Critical"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String urgencyValue;

    Urgency(String urgencyValue) {
        this.urgencyValue = urgencyValue;
    }

    public String getUrgencyString() {
        return this.urgencyValue;
    }
}
