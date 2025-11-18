package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/pets.db";

    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL);
        conn.setAutoCommit(true); // simpler for small projects
        createTableIfNotExists(conn);
        return conn;
    }

    private static void createTableIfNotExists(Connection conn) throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS pets (
                id TEXT PRIMARY KEY,
                name TEXT,
                type TEXT,
                breed TEXT,
                age TEXT,
                gender TEXT,
                size TEXT,
                contact TEXT
            );
        """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }
}

