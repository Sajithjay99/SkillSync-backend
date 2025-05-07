package com.springbootacademy.skill_sync.repo;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbootacademy.skill_sync.entity.Comment;

public interface CommentRepo extends MongoRepository<Comment, String> {
    List<Comment> findByPostId(String postId);
}
