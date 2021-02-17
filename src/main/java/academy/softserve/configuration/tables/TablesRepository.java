package academy.softserve.configuration.tables;

import academy.softserve.configuration.ConnectionConfig;

import java.sql.SQLException;
import java.sql.Statement;

public class TablesRepository {
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

    ConnectionConfig config = new ConnectionConfig("db.properties");

    public boolean createTables() {
        try (Statement statement = config.getConnection().createStatement()) {
            String query1 = new StringBuilder().append("create table if not exists ").append(TABLE_USER_NAME).append(" (")
                    .append(USER_ID).append(" serial primary key, ")
                    .append(USER_FIRST_NAME).append(" varchar not null, ")
                    .append(USER_LAST_NAME).append(" varchar not null, ")
                    .append(USER_PASSWORD).append(" varchar not null, ")
                    .append(USER_DATE_OF_BIRTH).append(" date not null, ")
                    .append(USER_EMAIL).append(" varchar not null, ")
                    .append(USER_ROLE).append(" varchar not null, ")
                    .append(USER_STATUS).append(" varchar not null)").toString();
            String query2 = new StringBuilder().append("create table if not exists ").append(TABLE_ADVERT_NAME).append(" (")
                    .append(ADVERT_ID).append(" serial primary key, ")
                    .append(ADVERT_TITLE).append(" varchar not null, ")
                    .append(ADVERT_DESCRIPTION).append(" varchar not null, ")
                    .append(ADVERT_PUBLISHING_DATE).append(" date not null, ")
                    .append(ADVERT_ENDING_DATE).append(" date not null, ")
                    .append(ADVERT_GENRE).append(" varchar not null, ")
                    .append(ADVERT_AUTHOR).append(" int not null constraint adverts_users_id_fk references ")
                    .append(TABLE_USER_NAME).append(");").toString();
            statement.execute(query1);
            statement.execute(query2);
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public boolean dropTables() {
        try (Statement statement = config.getConnection().createStatement()) {
            String query1 = "drop table if exists " + TABLE_ADVERT_NAME;
            String query2 = "drop table if exists " + TABLE_USER_NAME;
            statement.execute(query1);
            statement.execute(query2);
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}
