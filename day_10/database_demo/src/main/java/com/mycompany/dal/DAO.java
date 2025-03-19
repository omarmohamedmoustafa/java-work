package com.mycompany.dal;

/**
 *
 * @author omar
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.derby.jdbc.ClientDriver;




public class DAO {
    
    public static int register(UserDTO user) throws SQLException {
    int result = -1;
    
    // Check if the username already exists
    if (isUsernameExists(user.getUsername())) {
        System.out.println("UserID exists before. Try new one");
        return result; // Username already exists, return -1

    }
    
    // Register the new user
    try (Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/register", "root", "root");
         PreparedStatement statement = connection.prepareStatement("INSERT INTO UserTable (username, password) VALUES (?, ?)")) {
        
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        result = statement.executeUpdate();
        if (result > 0) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed: Unknown error.");
        }
    }
    
    return result;
}

private static boolean isUsernameExists(String username) throws SQLException {
    try (Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/register", "root", "root");
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserTable WHERE username = ?")) {
        
        statement.setString(1, username);
        try (java.sql.ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next(); // If a record is found, the username exists
        }
    }
}
    
    public static boolean login(UserDTO user) throws SQLException {
    boolean result = false;
    DriverManager.registerDriver(new ClientDriver());
    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/register", "root", "root");
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM UserTable WHERE username = ? AND password = ?");
    statement.setString(1, user.getUsername());
    statement.setString(2, user.getPassword());
    
    // Execute the query
    java.sql.ResultSet resultSet = statement.executeQuery();
    
    if (resultSet.next()) {
        result = true;
        System.out.println("Login successful!");

    } else {
        System.out.println("Login failed: Invalid username or password.");

    }
    
    // Close resources
    resultSet.close();
    statement.close();
    connection.close();
    
    return result;
    }
}