package com.springbootacademy.skill_sync.repo;

import com.springbootacademy.skill_sync.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends MongoRepository<Like, String> {

    List<Like> findByPostId(String postId);

    Optional<Like> findByPostIdAndUserEmail(String postId, String userEmail);

    long countByPostIdAndType(String postId, String type);
}
