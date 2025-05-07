package com.springbootacademy.skill_sync.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbootacademy.skill_sync.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
