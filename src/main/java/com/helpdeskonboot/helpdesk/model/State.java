package com.helpdeskonboot.helpdesk.model;

public enum State {
    NEW("New"),
    APPROVED("Approved"),
    DECLINED("Declined"),
    CANCELLED("Cancelled"),
    IN_PROGRESS("In progress"),
    DONE("Done");

    private final String stateValue;

    State(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return this.stateValue;
    }
}
