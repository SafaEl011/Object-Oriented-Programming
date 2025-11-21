import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static database.MenuHandler.showMenu;


public static void main(String[] args) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        System.out.println("Connected to database");

        showMenu(connection);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
