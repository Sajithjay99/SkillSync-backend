package com.springbootacademy.skill_sync.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springbootacademy.skill_sync.entity.UserConnection;

@Repository
public interface UserConnectionRepository extends MongoRepository<UserConnection, String> {
    UserConnection findByUserId(String userId);
}
