package com.model;

import com.enums.PointOfInterestType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eugen
 */

public class PointOfInterest {

    private int id;
    private String name;
    private String borough;
    private String location;
    private String description;
    private PointOfInterestType type;
    private int commentsCount;
    private List<Comment> comments;
    private boolean like;
    

    public PointOfInterest() {
    }

    public PointOfInterest(int id, String name, String borough, String location, String description, PointOfInterestType type, List<Comment> comments, boolean like) {
        this.id = id;
        this.name = name;
        this.borough = borough;
        this.location = location;
        this.description = description;
        this.type = type;
        this.comments = comments;
        this.like = like;
    }

public PointOfInterest(int id, String name, String borough, String location, String description, PointOfInterestType type) {
    this(id, name, borough, location, description, type, new ArrayList<Comment>(), false);
    // Assuming borough is a property of the PointOfInterest class, set it to null or a default value
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getBorough() {
        return borough;
    }

    public boolean isLike() {
        return like;
    }
    

    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PointOfInterestType getType() {
        return type;
    }

    public void setType(PointOfInterestType type) {
        this.type = type;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

}
