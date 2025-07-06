package com.example.Expense.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expense.services.UserServices;

import jakarta.servlet.http.HttpSession;

import com.example.Expense.model.Users;
import com.example.Expense.repository.UserRepository;

@RestController
@CrossOrigin(origins ="http://localhost:5173", allowCredentials = "true")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServices uservice;

    @Autowired
    UserRepository ur;

    @Autowired 
    HttpSession session;

   @PostMapping("/register")
    public ResponseEntity<?> r1(@RequestBody Users user) {
        String resp = uservice.registerUser(user);
        if (resp.equals("user Registered Successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body("user Registered Successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");

    }

    @PostMapping("/login")
    public ResponseEntity<?> l1(@RequestBody Users user) {
        String res = uservice.userLogin(user);
        if (res.equals("login successful")) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    @PutMapping("/update/{x}")
    public String u(@PathVariable String x, @RequestBody Users u) {
        Users existuser = ur.findByEmail(u.getEmail());
        if (existuser != null) {
            existuser.setPassword(x);
            ur.save(existuser);
            return "password update";
        }
        return "Invalid email";
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getprofile(){
        Integer id=(Integer) session.getAttribute("loggedInUser");
        String email=(String) session.getAttribute("logemail");
        Users user=ur.findByEmailAndId(email,id);
        if(email==null){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
        else if(user==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user");
        }
        else{
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("/updpro/{x}")
    public String profileupd(@RequestBody Users u,@PathVariable Long x){
        Users user=ur.findByEmail(u.getEmail());
        if(user!=null){
            user.setFirstName(u.getFirstName());
            user.setLastName(u.getLastName());
            user.setUsername(u.getUsername());
            user.setEmail(u.getEmail());
            user.setPhoneNum(u.getPhoneNum());
            user.setGender(u.getGender());
            ur.save(user);
            return ("Updated sucessfully");
        }
        return("Invalid email");
    }
}
