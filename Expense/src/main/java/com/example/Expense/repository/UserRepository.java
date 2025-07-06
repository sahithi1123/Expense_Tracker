package com.example.Expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Expense.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByEmail(String e);
    Users findById(long id);
    Users findByEmailAndId(String email, Integer id);
}

