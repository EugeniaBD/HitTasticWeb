package com.model;

import java.time.LocalDateTime;

//define a public class so it can be accesible from outside the package
public class Comment {
//define a Java Class  with the atributes below and their type
    //the class is public so it can be accesible from other classes
    //these are private variables can only be accessed within the Comment class itself and are not directly accessible from outside the class.
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

    //This method update the text of a comment added by a user
    //The method takes a single parameterrepresenting the new content for the comment.
    //The return type is void-method does not return any value.
    public void update(String text) {
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

    
//    getter and setter methods for various properties of the class.
//     The methods are related to the Comment class. 
    
    //allows external classes to retrieve the unique identifier of a Comment instance.
    public int getId() {
        return id;
    }
    
    // It allows external classes to update the unique identifier of a Comment instance.
    public void setId(int id) {
        this.id = id;
    }
    
    //allows external classes to retrieve the text content of a Comment
    public String getText() {
        return text;
    }
    
    //allows external classes to check whether a Comment instance is authorized or not.
    public boolean getAuthorise() {
        return authorise;
    }

    //allows external classes to update the authorization status of a Comment
    public void setAuthorise(boolean authorise) {
        this.authorise = authorise;
    }
    
    //allows external classes to update the text content of a Comment
    public void setText(String text) {
        this.text = text;
    }

    //retrieve or update  the timestamp of when a comment is made
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
