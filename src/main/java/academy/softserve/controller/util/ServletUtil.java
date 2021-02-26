package academy.softserve.controller.util;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class ServletUtil {
    private static final int WORK_LOAD = 12;

    private ServletUtil() {
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
}
