package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService service;

    // Signup
    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return service.save(user);
    }

    // Get all users
    @GetMapping("/all")
    public List<User> all() {
        return service.getAll();
    }

    // Get user by ID
    @GetMapping("/id/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Login
    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) {

        return service.login(email, password);
    }

    // Delete user
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "User deleted successfully";
    }
}