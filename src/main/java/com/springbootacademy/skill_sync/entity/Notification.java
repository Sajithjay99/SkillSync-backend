package com.springbootacademy.skill_sync.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "notifications")
@Getter
@Setter
public class Notification {
    @Id
    private String id;
    private String userId;
    private String message;
    private String description;
}