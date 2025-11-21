package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Museum {

    // Fields
    private int id;
    private String navn;
    private String sted;

    // Constructor
    public Museum(int id, String navn, String sted) {
        this.id = id;
        this.navn = navn;
        this.sted = sted;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getNavn() {
        return this.navn;
    }

    public String getSted() {
        return this.sted;
    }


    public void insertIntoDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO Museum (Id, Navn, Sted) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.id);
            preparedStatement.setString(2, this.navn);
            preparedStatement.setString(3, this.sted);
            preparedStatement.executeUpdate();
            System.out.println("Museum with ID " + this.id + " inserted successfully.");
        }
    }

}
