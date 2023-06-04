package com.helpdeskonboot.helpdesk.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "desired_resolution_date")
    private LocalDateTime desiredResolutionDate;

    @Column(name = "state")
    private State state;

    @Column(name = "urgency")
    private Urgency urgency;

    @ManyToOne
    @JoinColumn(name = "ticket_category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name = "asignee_id", referencedColumnName = "user_id")
    private User asignee;


    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)
    @JoinColumn(name = "Approver_id", referencedColumnName = "user_id")
    private User approver;


    public Ticket() {
    }

    public User getAsignee() {
        return asignee;
    }

    public void setAsignee(User asignee) {
        this.asignee = asignee;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }
}
