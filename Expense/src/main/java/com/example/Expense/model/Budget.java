package com.example.Expense.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private Users user;

    @Column(nullable=false)
    private int month;

    @Column(nullable=false)
    private int year;

    @Column(nullable=false)
    private double amount;

     public void setMonth(int m){
        this.month=m;
    }

    public void setAmount(double d){
        this.amount=d;
    }

    public int getMonth(){
        return this.month;
    }

    public long getId(){
        return this.id;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setUser(Users user){
        this.user=user;
    }

    public Users  getUser(){
        return this.user;
    }

    public void setYear(int y){
        this.year=y;
    }

    public int getYear(){
        return this.year;
    }
}

