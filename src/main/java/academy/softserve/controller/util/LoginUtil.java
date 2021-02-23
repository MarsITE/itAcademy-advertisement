package academy.softserve.controller.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class LoginUtil {
    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private final static SecretKey SECRET_KEY = MacProvider.generateKey(SIGNATURE_ALGORITHM);
    private final static TemporalAmount TIME_OF_TOKEN_VALIDITY = Duration.ofHours(1L);
    private final static int workload = 12;

    public static String createToken(final String password) {
        return Jwts.builder()
                .setSubject(password)
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
                .compact();
    }

    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(workload);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String storedHash) {
        return BCrypt.checkpw(password, storedHash);
    }

}
