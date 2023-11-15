package com.example.springbootdemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        System.out.printf("---StudentConfig");
        return args -> {
            Student jimmy = new Student(
                    "Jimmy",
                    "jimmy@qq.com.",
                    LocalDate.of(2000, Month.AUGUST, 5));
            Student alex = new Student(
                    "Alex",
                    "alex@qq.com.",
                    LocalDate.of(2004, Month.AUGUST, 5));

            studentRepository.saveAll(List.of(jimmy, alex));
        };
    }
}
