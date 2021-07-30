package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/hb_jdbc_exam";
        String user = "root";
        String password = "";

        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
