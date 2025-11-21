package database;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertDataIntoDatabase {

    public static void main(String[] args) throws ParseException {
        String fileName = "src/main/resources/funn.txt";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Read data from file and insert into database
            insertDataFromFile(conn, fileName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDataFromFile(Connection conn, String fileName) throws ParseException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split(";");
                if (data.length < 2) {
                    continue;
                }

                String type = data[0].trim();

                switch (type) {
                    case "Person":
                        insertPerson(conn, data);
                        break;
                    case "Museum":
                        insertMuseum(conn, data);
                        break;
                    case "Vaapen":
                        insertVaapen(conn, data);
                        break;
                    case "Smykke":
                        insertSmykke(conn, data);
                        break;
                    case "Mynt":
                        insertMynt(conn, data);
                        break;
                    default:
                        System.out.println("Ukjent type: " + type);
                }
            }
            System.out.println("Innsetting av data fullført.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertPerson(Connection conn, String[] data) throws SQLException {
        int id = Integer.parseInt(data[1].trim());
        String navn = data[2].trim();
        String tlf = data[3].trim();
        String epost = data[4].trim();

        String sql = "INSERT INTO Person (Id, Navn, Tlf, E_post) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, navn);
            pstmt.setString(3, tlf);
            pstmt.setString(4, epost);
            pstmt.executeUpdate();
        }
    }

    private static void insertMuseum(Connection conn, String[] data) throws SQLException {
        int id = Integer.parseInt(data[1].trim());
        String navn = data[2].trim();
        String sted = data[3].trim();

        String sql = "INSERT INTO Museum (Id, Navn, Sted) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, navn);
            pstmt.setString(3, sted);
            pstmt.executeUpdate();
        }
    }

    private static void insertVaapen(Connection conn, String[] data) throws SQLException, ParseException {
        int id = Integer.parseInt(data[1].trim());
        String funnsted = data[2].trim();
        int finnerId = Integer.parseInt(data[3].trim());
        Date funntidspunkt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[4].trim());
        int antattAarstall = Integer.parseInt(data[5].trim());
        int museumId = Integer.parseInt(data[6].trim());
        String type = data[7].trim();
        String materiale = data[8].trim();
        int vekt = Integer.parseInt(data[9].trim());

        String sql = "INSERT INTO Vaapen (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id, Type, Materiale, Vekt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, funnsted);
            pstmt.setInt(3, finnerId);
            pstmt.setTimestamp(4, new java.sql.Timestamp(funntidspunkt.getTime()));
            pstmt.setInt(5, antattAarstall);
            pstmt.setInt(6, museumId);
            pstmt.setString(7, type);
            pstmt.setString(8, materiale);
            pstmt.setInt(9, vekt);
            pstmt.executeUpdate();
        }
    }

    private static void insertSmykke(Connection conn, String[] data) throws SQLException, ParseException {
        int id = Integer.parseInt(data[1].trim());
        String funnsted = data[2].trim();
        int finnerId = Integer.parseInt(data[3].trim());
        Date funntidspunkt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[4].trim());
        int antattAarstall = Integer.parseInt(data[5].trim());
        int museumId = Integer.parseInt(data[6].trim());
        String type = data[7].trim();
        int verdiestimat = Integer.parseInt(data[8].trim());
        String filnavn = data[9].trim();

        String sql = "INSERT INTO Smykke (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id, Type, Verdiestimat, filnavn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, funnsted);
            pstmt.setInt(3, finnerId);
            pstmt.setTimestamp(4, new java.sql.Timestamp(funntidspunkt.getTime()));
            pstmt.setInt(5, antattAarstall);
            pstmt.setInt(6, museumId);
            pstmt.setString(7, type);
            pstmt.setInt(8, verdiestimat);
            pstmt.setString(9, filnavn);
            pstmt.executeUpdate();
        }
    }

    private static void insertMynt(Connection conn, String[] data) throws SQLException, ParseException {
        int id = Integer.parseInt(data[1].trim());
        String funnsted = data[2].trim();
        int finnerId = Integer.parseInt(data[3].trim());
        Date funntidspunkt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[4].trim());
        int antattAarstall = Integer.parseInt(data[5].trim());
        int museumId = Integer.parseInt(data[6].trim());
        int diameter = Integer.parseInt(data[7].trim());
        String metall = data[8].trim();

        String sql = "INSERT INTO Mynt (Id, Funnsted, Finner_id, Funntidspunkt, Antatt_aarstall, Museum_id, Diameter, Metall) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, funnsted);
            pstmt.setInt(3, finnerId);
            pstmt.setTimestamp(4, new java.sql.Timestamp(funntidspunkt.getTime()));
            pstmt.setInt(5, antattAarstall);
            pstmt.setInt(6, museumId);
            pstmt.setInt(7, diameter);
            pstmt.setString(8, metall);
            pstmt.executeUpdate();
        }
    }
}
