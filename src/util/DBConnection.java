package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Gives the rest of the project a JDBC Connection. Credentials come from db.properties. */
public class DBConnection {

    private static final Properties props = new Properties();

    static {
        try (FileInputStream in = new FileInputStream("db.properties")) {
            props.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(
                "Could not read db.properties. Copy db.properties.example and fill it in. " + e.getMessage());
        }
    }

    private DBConnection() { }

    /** Opens a new connection. Callers close it with try-with-resources. */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password"));
    }
}
