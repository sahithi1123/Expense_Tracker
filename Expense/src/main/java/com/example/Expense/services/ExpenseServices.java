package com.example.Expense.services;

import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.Expense.model.Budget;
import com.example.Expense.model.Expenses;
import com.example.Expense.model.Users;
import com.example.Expense.repository.BudgetRepository;
import com.example.Expense.repository.ExpenseRepository;
import com.example.Expense.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ExpenseServices {
    @Autowired
    ExpenseRepository er;

    @Autowired
    UserRepository ur;

    @Autowired
    BudgetRepository br;

    @Autowired
    HttpSession session; 

    public String add(Expenses e){
        Integer id = (Integer) session.getAttribute("loggedInUser");
        Users user = ur.findById(id).orElse(null);
        if(id==null){
            return "User not logged in";
        }
        if (user == null) {
            return "Invalid User!";
        }
        e.setUser(user);
        er.save(e);
        int month = e.getDate().getMonthValue();
        int year  = e.getDate().getYear();
        Double totalSpent = er.getMonthlyExpenseSum(user.getId(), month, year);
        if (totalSpent == null) totalSpent = 0.0;
        Budget budget = br.findByUserIdAndMonthAndYear(user.getId(), month, year);
        if (budget == null) {
            return "Expense added, but no budget set for " + Month.of(month) + " " + year;
        }
        if (totalSpent > budget.getAmount()) {
            return "Warning: You have exceeded your budget";
        }
        return "Expense is Added";
    }

    @Cacheable("hii")
    public List<Expenses> getData(String category){
         Integer id = (Integer) session.getAttribute("loggedInUser");
        return er.findByUserIdAndCategoryContainingIgnoreCase(id,category);
    }
    
}

