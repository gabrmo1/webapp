package br.com.webapp.infra;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Model model = null;
            FileReader reader;
            MavenXpp3Reader mavenreader = new MavenXpp3Reader();

            try {
                reader = new FileReader("pom.xml");
                model = mavenreader.read(reader);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            MavenProject project = new MavenProject(model);
            Properties properties = project.getProperties();
            String url = properties.getProperty("url.database");
            String user = properties.getProperty("user.database");
            String password = properties.getProperty("password.database");

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
