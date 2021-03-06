package academy.softserve.repository;

import academy.softserve.configuration.ConnectionConfig;
import academy.softserve.model.User;
import academy.softserve.model.library.UserRole;
import academy.softserve.model.library.UserStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static academy.softserve.configuration.tables.TablesRepository.*;

public class UserRepositoryImpl implements UserRepository {
    private final Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    ConnectionConfig config = new ConnectionConfig("db.properties");

    @Override
    public User save(User user) {
        String query = new StringBuilder().append("insert into ").append(TABLE_USER_NAME).append(" (")
                .append(USER_FIRST_NAME).append(", ")
                .append(USER_LAST_NAME).append(", ")
                .append(USER_PASSWORD).append(", ")
                .append(USER_DATE_OF_BIRTH).append(", ")
                .append(USER_EMAIL).append(", ")
                .append(USER_ROLE).append(", ")
                .append(USER_STATUS)
                .append(") values (?, ?, ?, ?, ?, ?, ?)").toString();
        try (PreparedStatement ps = config.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
            ps.setString(5, user.getEmail());
            ps.setString(6, String.valueOf(user.getUserRole()));
            ps.setString(7, String.valueOf(user.getUserStatus()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public User findById(long id) {
        String query = SELECT_FROM + TABLE_USER_NAME + WHERE + USER_ID + "=" + id;
        User user = null;
        try (Statement statement = config.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                user = getObject(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public User update(User user) {
        String query = new StringBuilder().append("update ").append(TABLE_USER_NAME).append(" set ")
                .append(USER_FIRST_NAME).append("=?, ")
                .append(USER_LAST_NAME).append("=?, ")
                .append(USER_PASSWORD).append("=?, ")
                .append(USER_DATE_OF_BIRTH).append("=?, ")
                .append(USER_EMAIL).append("=?, ")
                .append(USER_ROLE).append("=?, ")
                .append(USER_STATUS).append("=? where ")
                .append(USER_ID).append("=?").toString();
        try (PreparedStatement ps = config.getConnection().prepareStatement(query)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setDate(4, Date.valueOf(user.getDateOfBirth()));
            ps.setString(5, user.getEmail());
            ps.setString(6, String.valueOf(user.getUserRole()));
            ps.setString(7, String.valueOf(user.getUserStatus()));
            ps.setLong(8, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean delete(long id) {
        String query = "delete from " + TABLE_USER_NAME + WHERE + USER_ID + " = " + id;
        try (Statement statement = config.getConnection().createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = SELECT_FROM + TABLE_USER_NAME  + ORDER + USER_FIRST_NAME + ", " + USER_LAST_NAME;
        try (Statement statement = config.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                users.add(getObject(result));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return users;
    }

    @Override
    public User findByLogin(String login) {
        String query = SELECT_FROM + TABLE_USER_NAME + WHERE + USER_EMAIL + " = '" + login + "'";
        User user = null;
        try (Statement statement = config.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                user = getObject(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }

    @Override
    public List<String> findAllEmails() {
        List<String> emails = new ArrayList<>();
        String query = "select " + TABLE_USER_NAME + "." + USER_EMAIL + " from " + TABLE_USER_NAME;
        try (Statement statement = config.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                emails.add(result.getString("email"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return emails;
    }

    private User getObject(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt(USER_ID));
            user.setFirstName(rs.getString(USER_FIRST_NAME));
            user.setLastName(rs.getString(USER_LAST_NAME));
            user.setPassword(rs.getString(USER_PASSWORD));
            user.setDateOfBirth(rs.getDate(USER_DATE_OF_BIRTH).toLocalDate());
            user.setEmail(rs.getString(USER_EMAIL));
            user.setUserRole(UserRole.valueOf(rs.getString(USER_ROLE)));
            user.setUserStatus(UserStatus.valueOf(rs.getString(USER_STATUS)));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return user;
    }
}



