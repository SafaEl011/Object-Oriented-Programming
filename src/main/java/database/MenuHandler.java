package database;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuHandler {

    public static void showMenu(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n===== Main Menu =====");
                System.out.println("1. View all artifacts");
                System.out.println("2. View artifacts older than a specific year");
                System.out.println("3. View total number of artifacts");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        viewAllArtifacts(connection);
                        break;
                    case 2:
                        viewArtifactsOlderThan(connection, scanner);
                        break;
                    case 3:
                        viewTotalArtifacts(connection);
                        break;
                    case 4:
                        System.out.println("Exiting program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }

    }

    private static void viewAllArtifacts(Connection connection) {
        System.out.println("\n===== View All Artifacts =====");
        try {
            String query = "SELECT Id, Metall, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id, null as Type, null as Materiale, null as Vekt, null as Diameter FROM Mynt " +
                    "UNION SELECT Id, Type, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id, null as Metall, null as Materiale, null as Vekt, null as Diameter FROM Smykke " +
                    "UNION SELECT Id, Type, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id, null as Metall, Materiale, Vekt, null as Diameter FROM Vaapen";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                int count = 0;

                while (resultSet.next()) {
                    count++;

                    int id = resultSet.getInt("Id");
                    String type = resultSet.getString("Type");
                    String foundPlace = resultSet.getString("Funnsted");
                    int finderId = resultSet.getInt("Finner_id");
                    Date foundDate = resultSet.getDate("Funntidspunkt");
                    int estimatedYear = resultSet.getInt("Antatt_aarstall");
                    int museumId = resultSet.getInt("Museum_id");

                    System.out.println("\nID: " + id);
                    if (type != null) {
                        System.out.println("Type: " + type);
                    }
                    System.out.println("Coordinates found: " + foundPlace);
                    System.out.println("Finder ID: " + finderId);
                    System.out.println("Date found: " + foundDate);
                    System.out.println("Estimated From Year: " + estimatedYear);
                    System.out.println("Museum ID: " + museumId);

                    // Check for specific columns based on artifact type
                    if (resultSet.getString("Metall") != null) {
                        String metall = resultSet.getString("Metall");
                        System.out.println("Metall: " + metall);
                    }
                    if (resultSet.getString("Materiale") != null) {
                        String materiale = resultSet.getString("Materiale");
                        System.out.println("Materiale: " + materiale);
                        int vekt = resultSet.getInt("Vekt");
                        System.out.println("Vekt: " + vekt);
                    }
                    if (resultSet.getString("Diameter") != null) {
                        int diameter = resultSet.getInt("Diameter");
                        System.out.println("Diameter: " + diameter);
                    }

                    System.out.println("--------------------"); // Separator between artifacts
                }
                System.out.println("Total number of artifacts: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewArtifactsOlderThan(Connection connection, Scanner scanner) {
        System.out.println("\n===== View Artifacts Older Than =====");
        try {
            System.out.print("Enter year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            String query = "SELECT Id, null as Type, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id FROM Mynt WHERE Antatt_aarstall < ? " +
                    "UNION SELECT Id, Type, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id FROM Smykke WHERE Antatt_aarstall < ? " +
                    "UNION SELECT Id, Type, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id FROM Vaapen WHERE Antatt_aarstall < ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, year);
                preparedStatement.setInt(2, year);
                preparedStatement.setInt(3, year);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    int count = 0;
                    while (resultSet.next()) {
                        count++;

                        int id = resultSet.getInt("Id");
                        String type = resultSet.getString("Type"); // Check for null because `Mynt` does not have `Type` column
                        String foundPlace = resultSet.getString("Funnsted");
                        int finderId = resultSet.getInt("Finner_id");
                        Date foundDate = resultSet.getDate("Funntidspunkt");
                        int estimatedYear = resultSet.getInt("Antatt_aarstall");
                        int museumId = resultSet.getInt("Museum_id");

                        System.out.println("\nID: " + id);
                        if (type != null) {
                            System.out.println("Type: " + type);
                        }
                        System.out.println("Coordinates found: " + foundPlace);
                        System.out.println("Finder ID: " + finderId);
                        System.out.println("Date found: " + foundDate);
                        System.out.println("Estimated From Year: " + estimatedYear);
                        System.out.println("Museum ID: " + museumId);

                        System.out.println("--------------------"); // Separator between artifacts
                    }
                    System.out.println("Total number of artifacts older than " + year + ": " + count);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewTotalArtifacts(Connection connection) {
        System.out.println("\n===== Total Number of Artifacts =====");
        try {
            String query = "SELECT COUNT(*) AS total FROM Mynt UNION SELECT COUNT(*) FROM Smykke UNION SELECT COUNT(*) FROM Vaapen";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                int total = 0;
                while (resultSet.next()) {
                    total += resultSet.getInt("total");
                }
                System.out.println("Total number of artifacts: " + total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
