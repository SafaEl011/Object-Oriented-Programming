package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Weapon extends Artifact {

    private String type;
    private String material;
    private int weight;

    public Weapon(int id, double latitude, double longitude, int finderId, Date foundDate, int estimatedYear, Integer museumId, String type, String material, int weight) {
        super(id, latitude, longitude, finderId, foundDate, estimatedYear, museumId);
        this.type = type;
        this.material = material;
        this.weight = weight;
    }

    @Override
    public boolean exists(Connection connection) throws SQLException {
        String query = "SELECT 1 FROM vaapen WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public void insertIntoDatabase(Connection connection) throws SQLException {
        if (!exists(connection)) {
            String query = "INSERT INTO vaapen (id, funnsted, finner_id, funntidspunkt, antatt_aarstall, museum_id, type, materiale, vekt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, latitude + ", " + longitude);
                preparedStatement.setInt(3, finderId);
                preparedStatement.setDate(4, new java.sql.Date(dateFrom.getTime()));
                preparedStatement.setInt(5, estimatedYear);
                if (museumId != null) {
                    preparedStatement.setInt(6, museumId);
                } else {
                    preparedStatement.setNull(6, java.sql.Types.INTEGER);
                }
                preparedStatement.setString(7, type);
                preparedStatement.setString(8, material);
                preparedStatement.setInt(9, weight);
                preparedStatement.executeUpdate();
            }
        } else {
            System.out.println("Weapon with ID " + id + " already exists.");
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: " + type);
        System.out.println("Material: " + material);
        System.out.println("Weight: " + weight);
    }
}
