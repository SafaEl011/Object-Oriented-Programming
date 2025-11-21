package model;

import java.sql.Connection;
import java.sql.SQLException;

public interface FoundItem {
    boolean exists(Connection connection) throws SQLException;

    void insertIntoDatabase(Connection connection) throws SQLException;

    void displayDetails();
}
