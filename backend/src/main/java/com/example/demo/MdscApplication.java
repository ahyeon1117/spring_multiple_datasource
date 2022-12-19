package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.mysql.MySqlUserRepository;
import com.example.demo.repository.postgresql.PostgresUserRepository;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MdscApplication {

	@Autowired
	private MySqlUserRepository mySqlUserRepository;

	@Autowired
	private PostgresUserRepository postgresUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(MdscApplication.class, args);
	}

	@PostConstruct
	public void postgresqlTest(){
		log.info("postgresUser : ",postgresUserRepository.findAll());  
	}

	@PostConstruct
	public void mysqlTest(){
		log.info("mysqlUser : ",mySqlUserRepository.findAll());
		
	}

}
