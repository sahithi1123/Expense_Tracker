package com.example.Expense.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @Column(nullable=false)
    String firstName;
    @Column(nullable=false)
    String lastName;
    @Column(nullable=false)
    String username;
    @Column(nullable=false,unique=true)
    String email;
    @Column(nullable=false)
    String password;
    @Column(nullable=false)
    Long phoneNum;
    @Column(nullable=false)
    String gender;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Expenses> expense;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Budget> budget;
    public void setUsername(String u){
        this.username=u;
    }

    public void setEmail(String e){
        this.email=e;
    }

    public void setPassword(String p){
        this.password=p;
    }
    
    public int getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public Long getPhoneNum(){
        return this.phoneNum;
    }

    public void setLastName(String l){
        this.lastName=l;
    }

    public void setFirstName(String f){
        this.firstName=f;
    }

    public void setPhoneNum(Long n){
        this.phoneNum=n;
    }

    public void setGender(String g){
        this.gender=g;
    }

    public String getGender(){
        return this.gender;
    }

}

