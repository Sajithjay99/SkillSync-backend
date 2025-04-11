package com.springbootacademy.skill_sync.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String postId;
    private String userEmail;
    private String commentText;
    private Date timestamp;



    public Comment() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public void setuserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Comment(String id, String postId, String userEmail, String commentText, Date timestamp) {
        this.id = id;
        this.postId = postId;
        this.userEmail = userEmail;
        this.commentText = commentText;
        this.timestamp = timestamp;
    }




}

