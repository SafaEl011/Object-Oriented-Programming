package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Coin extends Artifact {

    private int diameter;
    private String metal;

    public Coin(int id, double latitude, double longitude, int finderId, Date foundDate, int estimatedYear, Integer museumId, int diameter, String metal) {
        super(id, latitude, longitude, finderId, foundDate, estimatedYear, museumId);
        this.diameter = diameter;
        this.metal = metal;
    }


    @Override
    public boolean exists(Connection connection) throws SQLException {
        String query = "SELECT 1 FROM Mynt WHERE Id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public void insertIntoDatabase(Connection connection) throws SQLException {
        if (!exists(connection)) {
            String query = "INSERT INTO Mynt (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id, Diameter, Metall) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, this.id);
                preparedStatement.setString(2, this.latitude + ", " + this.longitude); // Example of combining latitude and longitude
                preparedStatement.setInt(3, this.finderId);
                preparedStatement.setDate(4, new java.sql.Date(this.dateFrom.getTime()));
                preparedStatement.setInt(5, this.estimatedYear);
                if (this.museumId != null) {
                    preparedStatement.setInt(6, this.museumId);
                } else {
                    preparedStatement.setNull(6, java.sql.Types.INTEGER);
                }
                preparedStatement.setInt(7, diameter);
                preparedStatement.setString(8, metal);
                preparedStatement.executeUpdate();
                System.out.println("Coin with ID " + this.id + " inserted successfully.");
            }
        } else {
            System.out.println("Coin with ID " + this.id + " already exists.");
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Diameter: " + diameter);
        System.out.println("Metal: " + metal);
    }
}
