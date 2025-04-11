package com.springbootacademy.skill_sync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootacademy.skill_sync.entity.Notification;
import com.springbootacademy.skill_sync.repo.NotificationRepository;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }


}
