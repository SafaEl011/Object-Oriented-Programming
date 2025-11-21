package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Person {
    // Fields
    private int id;
    private String navn;
    private String tlf;
    private String ePost;

    // Constructor
    public Person(int id, String navn, String tlf , String ePost) {
        this.id = id;
        this.navn = navn;
        this.tlf = tlf;
        this.ePost = ePost;
    }

    // Getter
    public int getId() {
        return this.id;
    }

    public String getNavn() {
        return this.navn;
    }

    public String getTlf() {
        return this.tlf;
    }

    public String getEPost() {
        return this.ePost;
    }


    public void insertIntoDatabase(Connection connection) throws SQLException {
        String query = "INSERT INTO Person (Id, Navn, Tlf, E_post) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, this.id);
            preparedStatement.setString(2, this.navn);
            preparedStatement.setString(3, this.tlf);
            preparedStatement.setString(4, this.ePost);
            preparedStatement.executeUpdate();
            System.out.println("Person with ID " + this.id + " inserted successfully.");
        }
    }
}
