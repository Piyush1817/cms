package com.project.cms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cms.entity.Role;
import com.project.cms.entity.User;
import com.project.cms.repository.UserRepository;

@Service
public class UserService {

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

        // 3. Save user
        return userRepository.save(user);
    }
}