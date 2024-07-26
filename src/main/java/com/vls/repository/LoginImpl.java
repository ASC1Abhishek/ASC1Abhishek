package com.vls.repository;

import com.vls.exception.DatabaseException;
import com.vls.model.LoginModel;
import com.vls.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginImpl implements Login {

    @Override
    public LoginModel findByUsername(String username) {
        String sql = "SELECT * FROM login WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return new LoginModel(rs.getString("username"),
                        rs.getString("password"));
            } else {
                System.out.println("User not found for username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for more details
            throw new DatabaseException("Error finding user", e);
        }
        return null;
    }

    @Override
    public void add(LoginModel user) {
        String sql = "INSERT INTO login (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Print the stack trace for more details
            throw new DatabaseException("Error adding user", e);
        }
    }
}
