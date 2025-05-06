package com.springbootacademy.skill_sync.entity;

public class Account {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    @CreatedDate
    private LocalDate createdDate;
}