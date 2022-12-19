package com.example.demo.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.mysql.MySqlUser;

public interface  MySqlUserRepository  extends JpaRepository<MySqlUser, Integer> {
    
}
