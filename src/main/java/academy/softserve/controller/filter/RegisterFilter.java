package academy.softserve.controller.filter;

import academy.softserve.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.validation.ValidationException;
import java.io.IOException;


@WebFilter(urlPatterns = "/addUser")
public class RegisterFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(RegisterFilter.class);
    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (userService.findByLogin(request.getParameter("email")) != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
            request.setAttribute("ERROR", "Email already exists");
            dispatcher.forward(request, response);
            logger.error("Email already exists");
        } else {
            try {
                chain.doFilter(request, response);
            } catch (ValidationException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
