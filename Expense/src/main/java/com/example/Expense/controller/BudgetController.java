package com.example.Expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expense.model.Budget;
import com.example.Expense.repository.BudgetRepository;
import com.example.Expense.services.BudgetServices;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class BudgetController {
    @Autowired
    BudgetServices budgetservices;

    @Autowired
    BudgetRepository br;

    @Autowired
    HttpSession session;
    @PostMapping("/add")
    public ResponseEntity<?> addbudget(@RequestBody Budget b){
        String res=budgetservices.add(b); 
        if(res.equals("Budget is added")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } else if(res.equals("User not logged in")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        } else if(res.equals("Budget Already added for this month")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    @GetMapping
    public Page<Budget> getBudgets(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Integer id = (Integer) session.getAttribute("loggedInUser");
        Pageable pageable = PageRequest.of(page, size);
        return br.findByUserId(id, pageable);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> d(@PathVariable int id) {
        Budget t = br.findById(id).orElse(null); 
        br.delete(t);
        return ResponseEntity.ok("deleted");

    }
}

