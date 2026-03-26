package com.project.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}