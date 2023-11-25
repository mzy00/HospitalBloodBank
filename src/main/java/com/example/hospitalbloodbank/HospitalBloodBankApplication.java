package com.example.hospitalbloodbank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.hospitalbloodbank.mapper")
public class HospitalBloodBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalBloodBankApplication.class, args);
    }

}
