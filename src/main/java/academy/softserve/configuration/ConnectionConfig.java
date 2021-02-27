package academy.softserve.configuration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

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


    private void loadProperties(){
        properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream input = classLoader.getResourceAsStream(path)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        loadProperties();
        String url = properties.getProperty("JDBC_URL");
        String userName = properties.getProperty("JDBC_USER");
        String password = properties.getProperty("JDBC_PASSWORD");
        return DriverManager.getConnection(url, userName, password);
    }
}

