package orm;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
    @Getter
    private static Connection connection;

    private static final String connectionString = "jdbc:mysql://localhost:3306/";

    public static void createConnection(String username, String password, String dbName) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(connectionString + dbName, properties);
    }

}
