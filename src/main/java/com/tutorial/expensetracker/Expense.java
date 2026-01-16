package com.tutorial.expensetracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    public Expense(){
        this.description = "description";
        this.amount = new BigDecimal(0);
        this.dateTime = LocalDateTime.now();
    }
    public Expense(String description, BigDecimal amount, LocalDateTime dateTime){
        this.description = description;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Long getId() {
        return id;
    }
}
