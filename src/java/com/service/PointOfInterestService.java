package com.service;

import com.repository.DatabaseConnection;
import com.enums.PointOfInterestType;
import com.model.PointOfInterest;
import com.model.Comment;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointOfInterestService {

    public PointOfInterestService() {
    }

    public List<PointOfInterest> seacrhByType(PointOfInterestType type) throws ClassNotFoundException, SQLException, IOException {
        ArrayList<PointOfInterest> list = new ArrayList();
        PreparedStatement preparedStatement = DatabaseConnection.prepareStatement(""
                + "select poi.*, count(c.id) as comments "
                + "from point_of_interests poi "
                + "left join comments c on c.point_of_interest_id = poi.id "
                + "where poi.type = ? "
                + "group by poi.id ");

        preparedStatement.setString(1, type.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        fillList(resultSet, list);
        return list;
    }

    public List<PointOfInterest> searchByLocation(String location) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<PointOfInterest> list = new ArrayList();
        PreparedStatement preparedStatement = DatabaseConnection.prepareStatement(""
                + "select poi.*, count(c.id) as comments "
                + "from point_of_interests poi "
                + "left join comments c on c.point_of_interest_id = poi.id "
                + "where poi.location like ? "
                + "group by poi.id ");

        preparedStatement.setString(1, "%" + location + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        fillList(resultSet, list);
        return list;
    }

    public PointOfInterest findById(int id) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareStatement(""
                + "select poi.*, count(c.id) as comments "
                + "from point_of_interests poi "
                + "left join comments c on c.point_of_interest_id = poi.id "
                + "where poi.id = ?"
                + "group by poi.id ");

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        PointOfInterest poi = getPointOfInterestOrNull(resultSet);
        preparedStatement = DatabaseConnection.prepareStatement(""
                + "select c.*"
                + "from comments c where c.point_of_interest_id = ?");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        fillComments(resultSet, poi);
        return poi;
    }

    public void like(int id) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareStatement(""
                + "update point_of_interests set like = 1 where id = ?");

        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    private PointOfInterest getPointOfInterest(ResultSet resultSet) throws SQLException {
        PointOfInterest poi = new PointOfInterest();
        poi.setId(resultSet.getInt("id"));
        poi.setName(resultSet.getString("name"));
        poi.setType(PointOfInterestType.valueOf(resultSet.getString("type")));
        poi.setBorough(resultSet.getString("borough"));  
        poi.setLocation(resultSet.getString("location"));
        poi.setDescription(resultSet.getString("description"));
        poi.setLike(resultSet.getBoolean("like"));
        poi.setCommentsCount(resultSet.getInt("comments"));
        return poi;
    }

    private void fillList(ResultSet resultSet, ArrayList<PointOfInterest> list) throws SQLException {
        while (resultSet.next()) {
            PointOfInterest poi = getPointOfInterest(resultSet);
            list.add(poi);
        }
    }

    private PointOfInterest getPointOfInterestOrNull(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return getPointOfInterest(resultSet);
        }
        return null;
    }

    private void fillComments(ResultSet resultSet, PointOfInterest poi) throws SQLException {
        poi.setComments(new ArrayList<>());
        while (resultSet.next()) {
            poi.getComments().add(getComment(resultSet));
        }
    }

    private Comment getComment(ResultSet resultSet) throws SQLException {
        return new Comment(resultSet.getInt("id"), resultSet.getString("authorise").equals("1"), resultSet.getString("text"));
    }

    public void saveComment(int pointOfInterestId, String commentIdString, String text) throws SQLException, ClassNotFoundException, IOException {
        Integer commentId = commentIdString != null ? Integer.valueOf(commentIdString) : null;
        if (commentId != null) {
            updateComment(commentId, text);
        } else {
            saveComment(pointOfInterestId, text);
        }
    }

    private void updateComment(Integer commentId, String text) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareStatement("update comments set text = ? where id = ?");
        preparedStatement.setString(1, text);
        preparedStatement.setInt(2, commentId);
        preparedStatement.executeUpdate();
    }

    private void saveComment(int pointOfInterestId, String text) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareStatement("insert into comments (text, point_of_interest_id) values (?, ?)");
        preparedStatement.setString(1, text);
        preparedStatement.setInt(2, pointOfInterestId);
        preparedStatement.executeUpdate();
    }

    public int savePointOfInterest(String name, String location, String description, String borough, PointOfInterestType type) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareInsertStatement("insert into point_of_interests (name, borough, location, description, \"type\") values (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, borough);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, description);
        preparedStatement.setString(5, type.name());
        preparedStatement.executeUpdate();
        ResultSet resultSet = DatabaseConnection.createStatement().executeQuery("select max(id) as id from point_of_interests");
        resultSet.next();
        return resultSet.getInt("id");
    }

    public void deleteComment(int commentId) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareInsertStatement("delete from comments where id = ?");
        preparedStatement.setInt(1, commentId);
        preparedStatement.executeUpdate();
    }

    public void auhtoriseComment(int commentId) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareInsertStatement("update comments set authorise = not authorise where id = ?");
        preparedStatement.setInt(1, commentId);
        preparedStatement.executeUpdate();
    }

    public void deleteById(int id) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareInsertStatement("delete from point_of_interests where id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public void updatePointOfInterest(int id, String name, String location, String description, String borough, PointOfInterestType type) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement preparedStatement = DatabaseConnection.prepareInsertStatement("update point_of_interests set name = ?, borough = ?, location = ?, description = ?, type = ? where id = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, borough);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, description);
        preparedStatement.setString(5, type.name());
        preparedStatement.setInt(6, id);  // Corrected parameter index
        preparedStatement.executeUpdate();
    }
}
