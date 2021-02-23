package academy.softserve.controller.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static academy.softserve.controller.util.LoginUtil.*;

@WebFilter(urlPatterns = "/list")
public class LoginFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
/*
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashedPassword = hashPassword(password);

        HttpServletResponse resp = (HttpServletResponse) response;
        String path = "/login";

        if (!checkPassword(password, hashedPassword)) {
            request.setAttribute("ERROR", "Invalid email or password");
            logger.error("Invalid email or password");
            resp.sendRedirect(path);
        } else {
            request.setAttribute("TOKEN", new String[]{createToken(password)});
            chain.doFilter(request, resp);
        }*/
    }
}
