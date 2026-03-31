package com.project.cms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.cms.entity.Role;
import com.project.cms.entity.User;
import com.project.cms.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
  @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        // 1. Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }                                                       

        // 2. Assign default role
        if (user.getRole() == null) {
            user.setRole(Role.USER);     

        }
        // 3.  Encrypt password
    user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 4. Save user
        return userRepository.save(user);
    }

    
    public String loginUser(String email, String password) {

    // 1. Find user by email
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // 2. Check password
    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    // 3. Success
    return "Login successful!";
}

}