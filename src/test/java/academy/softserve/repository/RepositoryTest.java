package academy.softserve.repository;

import academy.softserve.configuration.ConnectionConfig;
import academy.softserve.configuration.tables.TablesRepository;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class RepositoryTest {
 //   ConnectionConfig connectionConfig = new ConnectionConfig("src/test/testresourses/db.properties");
    TablesRepository tr = new TablesRepository();

    @Test
    public void testCreateTables() {
        Assert.assertTrue(tr.createTables());
    }

/*    @Test(expectedExceptions = SQLException.class)
    public void testSQLException() throws SQLException {
        connectionConfig = new ConnectionConfig("src/test/testresourses/badDB.properties");
        connectionConfig.getConnection();
    }

    @Test(expectedExceptions = IOException.class)
    public void testIOException() throws SQLException {
        connectionConfig = new ConnectionConfig("db.properties");
        connectionConfig.getConnection();
    }*/
}
