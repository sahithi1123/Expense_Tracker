package com.example.Expense_Tracker_AI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Expense_Tracker_AI.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByEmail(String e);
    Users findByEmailAndId(String email, Integer id);
    Users findById(int id);
}

