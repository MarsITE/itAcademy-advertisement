package academy.softserve.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static academy.softserve.controller.LoginUtil.checkPassword;
import static academy.softserve.controller.LoginUtil.hashPassword;

//@WebFilter(urlPatterns = "/login")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = "";
        String password = "";

        if (request.getAttribute("username").toString() != null) {
            username = request.getAttribute("username").toString();
        }
        if (request.getAttribute("password").toString() != null) {
            password = request.getAttribute("password").toString();
        }
        String hashedPassword = "";
        if (!password.equals("")) {
            hashedPassword = hashPassword(password);
        }

        if (!username.contains("@") || password.length() > 15 || password.length() < 6 || !checkPassword(password, hashedPassword)) {
            request.setAttribute("error", "Wrong email or password");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
