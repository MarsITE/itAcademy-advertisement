package academy.softserve.repository;

import academy.softserve.configuration.ConnectionConfig;
import academy.softserve.model.Advert;
import academy.softserve.model.User;
import academy.softserve.model.library.AdvertGenre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static academy.softserve.configuration.tables.TablesRepository.*;

public class AdvertRepositoryImpl implements AdvertRepository {

    ConnectionConfig config = new ConnectionConfig("db.properties");

    @Override
    public Advert save(Advert advert) {
        String query = new StringBuilder().append("insert into ").append(TABLE_ADVERT_NAME).append(" (")
                .append(ADVERT_TITLE).append(", ")
                .append(ADVERT_DESCRIPTION).append(", ")
                .append(ADVERT_PUBLISHING_DATE).append(", ")
                .append(ADVERT_ENDING_DATE).append(", ")
                .append(ADVERT_GENRE).append(", ")
                .append(ADVERT_AUTHOR).append(") values (?, ?, ?, ?, ?, ?)").toString();
        try (PreparedStatement ps = config.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, advert.getTitle());
            ps.setString(2, advert.getDescription());
            ps.setDate(3, Date.valueOf(advert.getPublishingDate()));
            ps.setDate(4, Date.valueOf(advert.getEndingDate()));
            ps.setString(5, String.valueOf(advert.getAdvertGenre()));
            ps.setLong(6, advert.getAuthor().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                advert.setId(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return advert;
    }

    @Override
    public Advert findById(long id) {
        String query = SELECT_FROM + TABLE_ADVERT_NAME + WHERE + ADVERT_ID + "=" + id;
        Advert advert = null;
        try (Statement statement = config.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                advert = getObject(rs);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return advert;
    }

    @Override
    public Advert update(Advert advert) {
        String query = new StringBuilder().append("update ").append(TABLE_ADVERT_NAME).append(" set ")
                .append(ADVERT_TITLE).append("=?, ")
                .append(ADVERT_DESCRIPTION).append("=?, ")
                .append(ADVERT_PUBLISHING_DATE).append("=?, ")
                .append(ADVERT_ENDING_DATE).append("=?, ")
                .append(ADVERT_GENRE).append("=? where ")
                .append(ADVERT_ID).append("=?").toString();
        try (PreparedStatement ps = config.getConnection().prepareStatement(query)) {
            ps.setString(1, advert.getTitle());
            ps.setString(2, advert.getDescription());
            ps.setDate(3, Date.valueOf(advert.getPublishingDate()));
            ps.setDate(4, Date.valueOf(advert.getEndingDate()));
            ps.setString(5, String.valueOf(advert.getAdvertGenre()));
            ps.setLong(6, advert.getId());
            ps.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return advert;
    }

    @Override
    public boolean delete(long id) {
        String query = "delete from " + TABLE_ADVERT_NAME + WHERE + ADVERT_ID + " = " + id;
        try (Statement statement = config.getConnection().createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Advert> findAll() {
        List<Advert> adverts = new ArrayList<>();
        String query = SELECT_FROM + TABLE_ADVERT_NAME;
        try (Statement statement = config.getConnection().createStatement()) {
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                adverts.add(getObject(result));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return adverts;
    }

    private Advert getObject(ResultSet result) {
        Advert advert = new Advert();
        try {
            advert.setId(result.getInt(ADVERT_ID));
            advert.setTitle(result.getString(ADVERT_TITLE));
            advert.setDescription(result.getString(ADVERT_DESCRIPTION));
            advert.setPublishingDate(result.getDate(ADVERT_PUBLISHING_DATE).toLocalDate());
            advert.setEndingDate(result.getDate(ADVERT_ENDING_DATE).toLocalDate());
            advert.setAdvertGenre(AdvertGenre.valueOf(result.getString(ADVERT_GENRE)));
            User user = new User();
            user.setId(result.getInt("author"));
            UserRepositoryImpl userRepository = new UserRepositoryImpl();
            user = userRepository.findById(user.getId());
            advert.setAuthor(user);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return advert;
    }
}
