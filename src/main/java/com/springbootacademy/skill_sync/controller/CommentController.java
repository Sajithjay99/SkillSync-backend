package com.springbootacademy.skill_sync.controller;

import com.springbootacademy.skill_sync.entity.Comment;
import com.springbootacademy.skill_sync.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentRepo commentRepo;

    // POST: Create a new Comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, Authentication authentication) {
    comment.setuserEmail(authentication.getName()); // get email from JWT
    Comment saved = commentRepo.save(comment);
    return ResponseEntity.ok(saved);
}

    // GET: Retrieve all Comments
    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    // GET: Retrieve a Comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentRepo.findById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT: Update a Comment by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable String id,
                                       @RequestBody Comment commentDetails,
                                       Authentication authentication) {
    Optional<Comment> optionalComment = commentRepo.findById(id);

    if (optionalComment.isPresent()) {
        Comment comment = optionalComment.get();
        String currentUserEmail = authentication.getName();

        if (!comment.getuserEmail().equals(currentUserEmail)) {
            return ResponseEntity.status(403).body("🚫 You are not allowed to update this comment.");
        }

        comment.setCommentText(commentDetails.getCommentText());
        comment.setTimestamp(commentDetails.getTimestamp());
        return ResponseEntity.ok(commentRepo.save(comment));
    } else {
        return ResponseEntity.notFound().build();
    }
}


    // DELETE: Delete a Comment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable String id,
                                       Authentication authentication) {
    Optional<Comment> optionalComment = commentRepo.findById(id);

    if (optionalComment.isPresent()) {
        Comment comment = optionalComment.get();
        String currentUserEmail = authentication.getName();

        if (!comment.getuserEmail().equals(currentUserEmail)) {
            return ResponseEntity.status(403).body("🚫 You are not allowed to delete this comment.");
        }

        commentRepo.deleteById(id);
        return ResponseEntity.ok("✅ Comment deleted successfully.");
    } else {
        return ResponseEntity.notFound().build();
    }
}

}
