package com.revature.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
@Entity
@Table(name = "reimbursements")
@Component
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "reimbId")
    private int reimbId;

    @Column
    private String description;

    @Column
    private int amount;

    @Column(nullable = false)
    private String status = "pending";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;


    //boiler plate code
    public Reimbursement() {
    }

    public Reimbursement(int reimbId, String description, int amount, String status, User user) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }

    public Reimbursement(String description, int amount, String status, User user) {
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }
    public Reimbursement(User user) {
        this.user = user;
    }

    public int getReimbId() {
        return reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    //@Autowired //Setter Injection - this is fine practice, the injection will happen after instantation
    //This is often used if a dependency is optional
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}