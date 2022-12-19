package com.example.demo.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.postgresql.PostgresUser;

public interface  PostgresUserRepository  extends JpaRepository<PostgresUser, Integer> {
    
}
