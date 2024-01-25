package com.service;

import com.model.Role;
import com.model.User;
import com.repository.DatabaseConnection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eugen
 */

public class UserService {

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User register(String username, String password, String role) throws SQLException, ClassNotFoundException, IOException {
        User user = findByUsername(username);
        if (user != null) {
            return login(user.getUsername(), user.getPassword());
        }

        PreparedStatement prepareStatement = DatabaseConnection.prepareStatement("insert into users(username, password, role) values (?, ?, ?)");
        prepareStatement.setString(1, username);
        prepareStatement.setString(2, password);
        prepareStatement.setString(3, role);
        int executeUpdate = prepareStatement.executeUpdate();
        return login(username, password);
    }

    public User login(String username, String password) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement prepareStatement = DatabaseConnection.prepareStatement("select * from users u where u.username = ? and password = ?");
        prepareStatement.setString(1, username);
        prepareStatement.setString(2, password);
        ResultSet resultSet = prepareStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = getUser(resultSet);
        }
        return user;
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException, IOException {
        Statement statement = DatabaseConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users where role != 'Admin'");
        List<User> users = new ArrayList<>();
        fill(resultSet, users);
        return users;
    }

    private void fill(ResultSet resultSet, List<User> users) throws SQLException {
        while (resultSet.next()) {
            users.add(getUser(resultSet));
        }
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"), resultSet.getString("username"), Role.valueOf(resultSet.getString("role")));
    }

    private User findByUsername(String username) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement prepareStatement = DatabaseConnection.prepareStatement("select * from users u where u.username = ?");
        prepareStatement.setString(1, username);
        ResultSet resultSet = prepareStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = getUser(resultSet);
        }
        return user;
    }


    public void deleteById(int id) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement prepareStatement = DatabaseConnection.prepareStatement("delete from users where id = ?");
        prepareStatement.setInt(1, id);
        prepareStatement.executeUpdate();
    }

    public User findById(int id) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement prepareStatement = DatabaseConnection.prepareStatement("select * from users u where u.id = ?");
        prepareStatement.setInt(1, id);
        ResultSet resultSet = prepareStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = getUser(resultSet);
        }
        return user;
    }

    public void update(int id, String username, String password) throws SQLException, ClassNotFoundException, IOException {
        PreparedStatement prepareStatement = DatabaseConnection.prepareStatement("update users set username = ?, password = ? where id = ?");
        prepareStatement.setString(1, username);
        prepareStatement.setString(2, password);
        prepareStatement.setInt(3, id);
        prepareStatement.executeUpdate();
    }
}
