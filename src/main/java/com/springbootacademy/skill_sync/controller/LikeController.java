package com.springbootacademy.skill_sync.controller;

import com.springbootacademy.skill_sync.entity.Like;
import com.springbootacademy.skill_sync.repo.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeController(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    // Get all likes/dislikes for a post
    @GetMapping("/{postId}")
    public ResponseEntity<List<Like>> getLikesByPostId(@PathVariable String postId) {
        List<Like> likes = likeRepository.findByPostId(postId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    // Like or dislike a post
    @PostMapping
    public ResponseEntity<?> createLike(@RequestBody Like like, Authentication authentication) {
        String userEmail = authentication.getName();
        like.setUserEmail(userEmail);

        Optional<Like> existingLike = likeRepository.findByPostIdAndUserEmail(like.getPostId(), userEmail);
        
        if (existingLike.isPresent()) {
            Like existing = existingLike.get();
            existing.setType(like.getType()); // update like/dislike
            return new ResponseEntity<>(likeRepository.save(existing), HttpStatus.OK);
        }

        Like savedLike = likeRepository.save(like);
        return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
    }

    // Remove like/dislike by ID
    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable String likeId) {
        likeRepository.deleteById(likeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Count likes for a post
    @GetMapping("/{postId}/likes/count")
    public ResponseEntity<Long> countLikes(@PathVariable String postId) {
        long count = likeRepository.countByPostIdAndType(postId, "like");
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // Count dislikes for a post
    @GetMapping("/{postId}/dislikes/count")
    public ResponseEntity<Long> countDislikes(@PathVariable String postId) {
        long count = likeRepository.countByPostIdAndType(postId, "dislike");
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
