package academy.softserve.controller.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.mindrot.jbcrypt.BCrypt;
import javax.crypto.SecretKey;

public class LoginUtil {
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final SecretKey SECRET_KEY = MacProvider.generateKey(SIGNATURE_ALGORITHM);
    private static final int WORK_LOAD = 12;

    private LoginUtil() {
    }

    public static String createToken(final String password) {
        return Jwts.builder()
                .setSubject(password)
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
                .compact();
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(WORK_LOAD);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }

}
