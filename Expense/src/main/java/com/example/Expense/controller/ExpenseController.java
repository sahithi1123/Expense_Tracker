package com.example.Expense.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expense.model.Expenses;
import com.example.Expense.repository.ExpenseRepository;
import com.example.Expense.services.ExpenseServices;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/expense")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class ExpenseController {
    @Autowired
    ExpenseRepository er;

    @Autowired
    HttpSession session; 

    @Autowired
    ExpenseServices expenseService;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody Expenses expense) {
        String res = expenseService.add(expense);
        if(res.equals("Expense is Added")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } else if(res.equals("User not logged in")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        } else if(res.equals("Warning: You have exceeded your budget")){
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }

    @GetMapping
    public Page<Expenses> getExpenses(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size) {
        Integer id = (Integer) session.getAttribute("loggedInUser");
        Pageable pageable = PageRequest.of(page, size);
        return er.findByUserId(id,pageable);
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> d(@PathVariable int id) {
        Expenses t = er.findById(id).orElse(null);
        er.delete(t);
        return ResponseEntity.ok("deleted");

    }

    @PutMapping("/update/{id}")
    ResponseEntity<?> u(@PathVariable int id, @RequestBody Expenses e) {
        Expenses expense = er.findById(id).orElse(null);
        expense.setAmount(e.getAmount());
        expense.setCategory(e.getCategory());
        expense.setDate(e.getDate());
        expense.setDescription(e.getDescription());
        er.save(expense);
        return ResponseEntity.ok("updated..");
    }

    @GetMapping("/search")
    public List<Expenses> searchByName(@RequestParam String category){
        return expenseService.getData(category);
    }
}
