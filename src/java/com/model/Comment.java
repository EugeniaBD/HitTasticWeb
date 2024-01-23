package com.model;

import java.time.LocalDateTime;

//define a public class so it can be accesible from outside the package
public class Comment {
//define a Java Class  with the atributes below and their type
    //the class is public so it can be accesible from other classes
    private int id;
    private String text;
    private boolean authorise;// this variable can be true or false
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    

    public Comment(int id, boolean authorise, String text) {
        this.id = id; // id is a unigue identifier for the comment
        this.authorise = authorise; //A boolean variable that likely indicates whether the comment is authorized or not.
        this.text = text;
        this.createdAt = LocalDateTime.now();//A variable of type LocalDateTime to store the timestamp when the comment was created.
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String text) {
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

    
//    getter and setter for the used functions
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
