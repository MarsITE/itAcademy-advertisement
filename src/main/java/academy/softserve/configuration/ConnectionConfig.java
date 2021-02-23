package academy.softserve.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {
    private final static Logger logger = LogManager.getLogger(ConnectionConfig.class);

    private String path;

    public ConnectionConfig(String path) {
        this.path = path;
    }

    Properties properties;


    private boolean loadProperties(){
        properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(path)) {
            if (input != null) {
                properties.load(input);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadProperties();
        String url = properties.getProperty("JDBC_URL");
        String userName = properties.getProperty("JDBC_USER");
        String password = properties.getProperty("JDBC_PASSWORD");
        return DriverManager.getConnection(url, userName, password);
    }
}

