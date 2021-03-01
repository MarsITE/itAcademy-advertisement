package academy.softserve.configuration.tables;

import academy.softserve.configuration.ConnectionConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public class TablesRepository {
    private final static Logger logger = LogManager.getLogger(TablesRepository.class);

    public static final String TABLE_USER_NAME = "Users";
    public static final String USER_ID = "id";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_PASSWORD = "password";
    public static final String USER_DATE_OF_BIRTH = "dateOfBirth";
    public static final String USER_EMAIL = "email";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "status";

    public static final String TABLE_ADVERT_NAME = "Adverts";
    public static final String ADVERT_ID = "id";
    public static final String ADVERT_TITLE = "title";
    public static final String ADVERT_DESCRIPTION = "description";
    public static final String ADVERT_PUBLISHING_DATE = "publishingDate";
    public static final String ADVERT_ENDING_DATE = "endingDate";
    public static final String ADVERT_GENRE = "genre";
    public static final String ADVERT_AUTHOR = "author";

    public static final String VARCHAR = " varchar not null, ";
    public static final String DROP_TABLE = "drop table if exists ";
    public static final String CREATE_TABLE = "create table if not exists ";
    public static final String SELECT_FROM = "select * from ";
    public static final String WHERE = " where ";
    public static final String ORDER = " order by ";
    public static final String DESC = " desc, ";

    ConnectionConfig config = new ConnectionConfig("db.properties");

    public boolean createTables() {
        try (Statement statement = config.getConnection().createStatement()) {
            String query1 = CREATE_TABLE + TABLE_USER_NAME + " (" +
                    USER_ID + " serial primary key, " +
                    USER_FIRST_NAME + VARCHAR +
                    USER_LAST_NAME + VARCHAR +
                    USER_PASSWORD + VARCHAR +
                    USER_DATE_OF_BIRTH + VARCHAR +
                    USER_EMAIL + VARCHAR +
                    USER_ROLE + VARCHAR +
                    USER_STATUS + " varchar not null)";
            String query2 = CREATE_TABLE + TABLE_ADVERT_NAME + " (" +
                    ADVERT_ID + " serial primary key, " +
                    ADVERT_TITLE + VARCHAR +
                    ADVERT_DESCRIPTION + VARCHAR +
                    ADVERT_PUBLISHING_DATE + VARCHAR +
                    ADVERT_ENDING_DATE + " date not null, " +
                    ADVERT_GENRE + VARCHAR +
                    ADVERT_AUTHOR + " int not null constraint adverts_users_id_fk references " +
                    TABLE_USER_NAME + ");";
            statement.execute(query1);
            statement.execute(query2);
            logger.info("Create tables Users and Adverts");
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean dropTables() {
        try (Statement statement = config.getConnection().createStatement()) {
            String query1 = DROP_TABLE + TABLE_ADVERT_NAME;
            String query2 = DROP_TABLE + TABLE_USER_NAME;
            statement.execute(query1);
            statement.execute(query2);
            logger.info("Drop tables Users and Adverts");
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
