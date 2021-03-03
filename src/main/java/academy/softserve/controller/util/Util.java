package academy.softserve.controller.util;

import academy.softserve.model.Advert;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Util {
    private static final int WORK_LOAD = 12;
    public static final String LOGIN = "username";
    public static final String PASSWORD = "password";
    public static final String USER_ID = "userId";
    public static final String ADVERT_ID = "advertId";
    public static final String ADVERT_GENRE = "advertGenre";
    public static final String ADVERTS = "adverts";
    public static final String ADVERTS_ROOT = "/WEB-INF/pages/advert-list.jsp";
    public static final String EMAIL = "email";

    private Util() {
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(WORK_LOAD);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }

    public static LocalDate convertSqlDateToLocalDate(String date) {
        return Optional.ofNullable(Date.valueOf(date))
                .map(Date::toLocalDate)
                .orElse(null);
    }

    public static List<Advert> actualAdverts(List<Advert> adverts) {
        List<Advert> actualAdverts = new ArrayList<>();
        for (Advert advert : adverts) {
            if (advert.getEndingDate().isAfter(LocalDate.now())
                    && advert.getPublishingDate().isBefore(LocalDate.now())) {
                actualAdverts.add(advert);
            }
        }
        return actualAdverts;
    }
}
