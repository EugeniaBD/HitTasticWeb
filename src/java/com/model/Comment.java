package com.model;

import java.time.LocalDateTime;

public class Comment {

    private int id;
    private String text;
    private boolean authorise;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(int id, boolean authorise, String text) {
        this.id = id;
        this.authorise = authorise;
        this.text = text;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String text) {
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public boolean getAuthorise() {
        return authorise;
    }

    public void setAuthorise(boolean authorise) {
        this.authorise = authorise;
    }
    

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }



}
