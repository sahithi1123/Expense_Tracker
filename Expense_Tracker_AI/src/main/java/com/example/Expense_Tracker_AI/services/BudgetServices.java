package com.example.Expense_Tracker_AI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Expense_Tracker_AI.model.Budget;
import com.example.Expense_Tracker_AI.model.Users;
import com.example.Expense_Tracker_AI.repository.BudgetRepository;
import com.example.Expense_Tracker_AI.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class BudgetServices {
    @Autowired
    HttpSession session;

    @Autowired
    BudgetRepository budgetrepository;

    @Autowired
    UserRepository  userrepository;

    public String add(Budget b){
        Integer id = (Integer) session.getAttribute("loggedInUser");
        if(id==null){
            return "User not logged in";
        }
        Users user = userrepository.findById(id).orElse(null);
        if (user == null) {
            return "Invalid User!";
        }
        Budget u=budgetrepository.findByUserAndMonth(user,b.getMonth());
        if(u!=null){
            return "Budget Already added for this month";
        }
        b.setUser(user);
        budgetrepository.save(b);
        return "Budget is added";
    }
}

