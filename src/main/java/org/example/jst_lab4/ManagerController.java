package org.example.jst_lab4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    public List<Manager> getAllManagers() throws SQLException {
        String query = "SELECT * FROM managers";
        List<Manager> managers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                managers.add(new Manager(
                        resultSet.getInt("manager_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("email"),
                        resultSet.getString("mobile_number"),
                        resultSet.getString("address"),
                        resultSet.getString("passport_number")
                ));
            }
        }
        return managers;
    }

    public void addManager(Manager manager) throws SQLException {
        String query = "INSERT INTO managers (full_name, email, mobile_number, address, passport_number) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, manager.getFullName());
            preparedStatement.setString(2, manager.getEmail());
            preparedStatement.setString(3, manager.getMobileNumber());
            preparedStatement.setString(4, manager.getAddress());
            preparedStatement.setString(5, manager.getPassportNumber());
            preparedStatement.executeUpdate();
        }
    }

    public void updateManager(Manager manager) throws SQLException {
        String query = "UPDATE managers SET full_name = ?, email = ?, mobile_number = ?, address = ?, passport_number = ? WHERE manager_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, manager.getFullName());
            preparedStatement.setString(2, manager.getEmail());
            preparedStatement.setString(3, manager.getMobileNumber());
            preparedStatement.setString(4, manager.getAddress());
            preparedStatement.setString(5, manager.getPassportNumber());
            preparedStatement.setInt(6, manager.getManagerId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteManager(int managerId) throws SQLException {
        String query = "DELETE FROM managers WHERE manager_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, managerId);
            preparedStatement.executeUpdate();
        }
    }
}

