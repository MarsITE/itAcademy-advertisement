package academy.softserve.controller;

import academy.softserve.model.User;
import academy.softserve.model.library.UserRole;
import academy.softserve.model.library.UserStatus;
import academy.softserve.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    private final UserService userService = new UserService();
    List<User> users;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userService.findAll());
        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");

        if (userId != null){
            userService.update(setUser(request));
        } else {
            userService.save(setUser(request));
        }

        request.setAttribute("users", userService.findAll());

        request.getRequestDispatcher("/WEB-INF/pages/users.jsp").forward(request, response);
    }

    private User setUser(HttpServletRequest request){
        User user = new User();
        return user.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .password(request.getParameter("password"))
                .dateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")))
                .email(request.getParameter("email"))
                .userRole(UserRole.valueOf(request.getParameter("userRole")))
                .userStatus(UserStatus.valueOf(request.getParameter("userStatus")))
                .build();
    }



}
