package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Jewelry extends Artifact {
    private String type;
    private int valueEstimate;
    private String filename;

    public Jewelry(int id, double latitude, double longitude, int finderId, Date foundDate, int estimatedYear, Integer museumId, String type, int valueEstimate, String filename) {
        super(id, latitude, longitude, finderId, foundDate, estimatedYear, museumId);
        this.type = type;
        this.valueEstimate = valueEstimate;
        this.filename = filename;
    }

    public boolean exists(Connection connection) throws SQLException {
        String query = "SELECT 1 FROM smykke WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        boolean var5;
        try {
            preparedStatement.setInt(1, this.id);
            ResultSet resultSet = preparedStatement.executeQuery();

            try {
                var5 = resultSet.next();
            } catch (Throwable var9) {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Throwable var10) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var7) {
                    var10.addSuppressed(var7);
                }
            }

            throw var10;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        return var5;
    }

    public void insertIntoDatabase(Connection connection) throws SQLException {
        if (!this.exists(connection)) {
            String query = "INSERT INTO smykke (id, funnsted, finner_id, funntidspunkt, antatt_aarstall, museum_id, type, verdiestimat, filnavn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                preparedStatement.setInt(1, this.id);
                preparedStatement.setString(2, this.latitude + ", " + this.longitude);
                preparedStatement.setInt(3, this.finderId);
                preparedStatement.setDate(4, new java.sql.Date(this.dateFrom.getTime()));
                preparedStatement.setInt(5, this.estimatedYear);
                if (this.museumId != null) {
                    preparedStatement.setInt(6, this.museumId);
                } else {
                    preparedStatement.setNull(6, 4);
                }

                preparedStatement.setString(7, this.type);
                preparedStatement.setInt(8, this.valueEstimate);
                preparedStatement.setString(9, this.filename);
                preparedStatement.executeUpdate();
            } catch (Throwable var7) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } else {
            System.out.println("Jewelry with ID " + this.id + " already exists.");
        }

    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type: " + this.type);
        System.out.println("Value Estimate: " + this.valueEstimate);
        System.out.println("Filename: " + this.filename);
    }
}
