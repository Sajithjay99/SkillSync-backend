package com.springbootacademy.skill_sync.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "learning")
public class Learning {
    @Id
    private String id;
    private String userId;
    private String topic;
    private String description;
    private String resourceLink;
    private String status;
    private LocalDateTime timestamp;
    private String reflection;
    private String template;

    // Template-specific fields (remaining)
    private String certificationName;   //  Certification
    private String provider;            // Certificationand
    private String dateObtained;        // Certification

    private String challengeName;       // Competitions
    private String result;              //Competitions
}
 
