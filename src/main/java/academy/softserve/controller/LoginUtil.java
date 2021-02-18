package academy.softserve.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKey;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class LoginUtil {
    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private final static SecretKey SECRET_KEY = MacProvider.generateKey(SIGNATURE_ALGORITHM);
    private final static TemporalAmount TIME_OF_TOKEN_VALIDITY = Duration.ofHours(1L);

    private final static int workload = 12;

    public static ServletContext servletContext;

    public static void dispatcher(HttpServletRequest req, HttpServletResponse resp, RequestDispatcher rd) {
        try {
            rd.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public static String createHashCodedPassword(final String password) {
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
