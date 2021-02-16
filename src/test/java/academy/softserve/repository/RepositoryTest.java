/*package academy.softserve.repository;

import academy.softserve.configuration.ConnectionConfig;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

public class RepositoryTest {
    ConnectionConfig connectionConfig;
    TablesRepository tr = new TablesRepository();

    @BeforeMethod
    public void SetUp() {
        connectionConfig = new ConnectionConfig(Paths.get("src/test/testresourses/db.properties"));
    }

    @Test(expectedExceptions = SQLException.class)
    public void testSQLException() throws SQLException, IOException {
        connectionConfig = new ConnectionConfig(Paths.get("src/test/testresourses/badDB.properties"));
        connectionConfig.getConnection();
    }

    @Test(expectedExceptions = IOException.class)
    public void testIOException() throws SQLException, IOException {
        connectionConfig = new ConnectionConfig(Paths.get("db.properties"));
        connectionConfig.getConnection();
    }
}*/
