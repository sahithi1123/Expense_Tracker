package com.example.Expense.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Expense.model.Users;
import com.example.Expense.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServices {

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository ur;
    
    public String registerUser(Users user) {
        Users existuser = ur.findByEmail(user.getEmail());
        if (existuser != null) {
            return "Email already exists";
        }
        ur.save(user);
        return "user Registered Successfully";

    }

    public String userLogin(Users user) {
        Users existuser = ur.findByEmail(user.getEmail());
        if (existuser != null && existuser.getPassword().equals(user.getPassword())) {
            session.setAttribute("loggedInUser", existuser.getId());
            session.setAttribute("logemail", existuser.getEmail());
            return "login successful";
        }
        return "Invalid email or password";
    }

   

}


