package com.solvd.pageranked.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtil {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionUtil.class);

    public static Connection getConnection() {
        Connection connection = null;
        try (FileInputStream f = new FileInputStream("src/main/resources/db.properties")) {

            Properties properties = new Properties();
            properties.load(f);

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) connection.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }
}
