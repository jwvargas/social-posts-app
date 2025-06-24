package com.evaluacion.socialposts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TwitterData {
    @JsonProperty("data")
    private List<TwitterPost> data;

    public List<TwitterPost> getData() {
        return data;
    }

    public void setData(List<TwitterPost> data) {
        this.data = data;
    }

    public static class TwitterPost {
        @JsonProperty("id")
        private String id;
        
        @JsonProperty("text")
        private String text;
        
        @JsonProperty("created_at")
        private String createdAt;
        
        @JsonProperty("name")
        private String name;
        
        @JsonProperty("username")
        private String username;
        
        @JsonProperty("likes")
        private int likes;
        
        @JsonProperty("comments")
        private int comments;
        
        @JsonProperty("shares")
        private int shares;

        // Getters and setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        
        public String getCreatedAt() { return createdAt; }
        public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public int getLikes() { return likes; }
        public void setLikes(int likes) { this.likes = likes; }
        
        public int getComments() { return comments; }
        public void setComments(int comments) { this.comments = comments; }
        
        public int getShares() { return shares; }
        public void setShares(int shares) { this.shares = shares; }
    }
}
