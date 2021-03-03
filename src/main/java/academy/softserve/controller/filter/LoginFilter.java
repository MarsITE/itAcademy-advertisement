package academy.softserve.controller.filter;

import academy.softserve.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static academy.softserve.controller.util.Util.checkPassword;


@WebFilter(urlPatterns = "/login")
public class LoginFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoginFilter.class);
    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");

        if (userService.findByLogin(username) == null) {
            request.setAttribute("ERROR", "Invalid login");
            dispatcher.forward(request, response);
            logger.error("Invalid login");
        } else if (!checkPassword(password, userService.findByLogin(username).getPassword())) {
            request.setAttribute("ERROR", "Invalid password");
            dispatcher.forward(request, response);
            logger.error("Invalid password");
        } else {
            chain.doFilter(request, response);
        }

    }
}
