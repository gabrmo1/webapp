package br.com.webapp.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            String url = ""; // exemplo : jdbc:mysql://localhost:3306/webapp
            String user = "";
            String password = "";

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
