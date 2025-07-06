package com.example.Expense.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private double amount;

    @Column(nullable=false)
    private String category;

   @Column(nullable=false)
    private LocalDate date;

    @Column(nullable=false)
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private Users user;

    public void setAmount(double a){
        this.amount=a;
    }

    public void setCategory(String c){
        this.category=c;
    }

    public void setDate(LocalDate d){
        this.date=d;
    }

    public void setDescription(String d){
        this.description=d;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getCategory(){
        return this.category;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String getDescription(){
        return this.description;
    }
    
    public int getId(){
        return this.id;
    }

    public void setUser(Users u){
        this.user=u;
    }

    public Users getUser(){
        return this.user;
    }

    public void setId(int id){
        this.id=id;
    }
}

