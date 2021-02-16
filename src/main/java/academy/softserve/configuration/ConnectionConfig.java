package academy.softserve.configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {

//    private Path path;

/*    public ConnectionConfig(Path path) {
        this.path = path;
    }*/

    public ConnectionConfig() {
    }

    public Connection getConnection() throws SQLException, IOException {
/*        Properties properties = new Properties();
        try (InputStream is = Files.newInputStream(path)) {
            properties.load(is);
            String url = properties.getProperty("JDBC_URL");
            String userName = properties.getProperty("JDBC_USER");
            String password = properties.getProperty("JDBC_PASSWORD");*/
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String userName = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, userName, password);
    }
}

