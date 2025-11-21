package database;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class DatabaseConnection {

    // Properties object to hold database connection properties
    private static final Properties properties = new Properties();


    static {
        try {
            // Load properties from database.properties file
            properties.load(new FileInputStream("src/main/resources/files/database.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }

        List<String> keysNeeded = List.of(
                "host", "port", "database", "username", "password"
        );

        if (!properties.keySet().containsAll(keysNeeded)) {
            System.out.print("Keys that exist: ");
            System.out.println(properties.keySet());

            throw new RuntimeException("Missing information to connect to database! Required keys: " + keysNeeded);
        }

    }

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                STR."\{"jdbc:mysql://%s:%s/%s".formatted(
                        properties.getProperty("host"),
                        properties.getProperty("port"),
                        properties.getProperty("database")
                )}?allowPublicKeyRetrieval=true&useSSL=false",
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    };

}
