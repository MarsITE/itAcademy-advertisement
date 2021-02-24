package academy.softserve.controller;

import academy.softserve.model.Advert;
import academy.softserve.model.User;
import academy.softserve.model.library.AdvertGenre;
import academy.softserve.model.library.UserRole;
import academy.softserve.model.library.UserStatus;
import academy.softserve.service.AdvertServiceImpl;
import academy.softserve.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

import static academy.softserve.controller.util.LoginUtil.*;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    private final AdvertServiceImpl advertService = new AdvertServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final String subject = "token";
    private String token = "";
    Advert advert;
    User user;
    User currentUser;
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertAdvert(request, response);
                break;
            case "/delete":
                deleteAdvert(request, response);
                break;
            case "/deleteUser":
                deleteUser(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateAdvert(request, response);
                break;
            case "/editUser":
                userEditForm(request, response);
                break;
            case "/updateUser":
                updateUser(request, response);
                break;
            case "/advert-info":
                infoAdvert(request, response);
                break;
            case "/registration":
                userRegistration(request, response);
                break;
            case "/login":
                loginUser(request, response);
                break;
            case "/addUser":
                addUser(request, response);
                break;
            case "/signin":
                userSignIn(request, response);
                break;
            case "/userlist":
                listUser(request, response);
                break;
            case "/logOut":
                logOut(request, response);
                break;
            case "/list":
                listAdvert(request, response);
                break;
            default:
                listAdvertUser(request, response);
                break;
        }
    }

    private void listAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (currentUser == null) {
            currentUser = userService.findByLogin(request.getParameter("username"));
        }
        session = request.getSession();

        session.setAttribute("currentUser", currentUser);

        request.setAttribute("adverts", advertService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list.jsp");
        dispatcher.forward(request, response);
    }

    private void listAdvertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (currentUser == null) {
            currentUser = userService.findByLogin(request.getParameter("username"));
        }
        session = request.getSession();

        session.setAttribute("currentUser", currentUser);

        request.setAttribute("adverts", advertService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list-user.jsp");
        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("users", userService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //   request.setAttribute("advertGenre", AdvertGenre.values());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("advertId"));
        Advert existingAdvert = advertService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-form.jsp");
        request.setAttribute("advert", existingAdvert);
        dispatcher.forward(request, response);

    }

    private void insertAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        advert = advert.builder()
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .publishingDate(LocalDate.parse(request.getParameter("publishingDate")))
                .endingDate(LocalDate.parse(request.getParameter("endingDate")))
                .advertGenre(AdvertGenre.getByName(request.getParameter("advertGenre")))
                .author(currentUser)
                .build();
        advertService.save(advert);
        response.sendRedirect("/");
    }

    private void updateAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long advertId = Integer.parseInt(request.getParameter("advertId"));
        advert = advert.builder().id(advertId)
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .publishingDate(LocalDate.parse(request.getParameter("publishingDate")))
                .endingDate(LocalDate.parse(request.getParameter("endingDate")))
                .advertGenre(AdvertGenre.getByName(request.getParameter("advertGenre")))
                .build();

        advertService.update(advert);
        response.sendRedirect("/");
    }

    private void deleteAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long advertId = Long.parseLong(request.getParameter("advertId"));
        advertService.delete(advertId);
        response.sendRedirect("/");
    }

    private void infoAdvert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("advertId"));
        Advert existingAdvert = advertService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-info.jsp");
        request.setAttribute("advert", existingAdvert);
        dispatcher.forward(request, response);
    }


    private void userRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/registration-form.jsp");
        dispatcher.forward(request, response);
    }

    private void userEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("userId"));
        User existingUser = userService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/registration-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void userSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/sign-in-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String password = request.getParameter("password");
        String hashedPassword = hashPassword(password);

        user = user.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .password(hashedPassword)
                .dateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")))
                .email(request.getParameter("email"))
                .userRole(UserRole.USER)
                .userStatus(UserStatus.NEWCOMER)
                .build();
        userService.save(user);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/userlist");
        rd.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Integer.parseInt(request.getParameter("userId"));
          user = user.builder()
                .id(userId)
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .password(request.getParameter("password"))
                .dateOfBirth(LocalDate.parse(request.getParameter("dateOfBirth")))
                .email(request.getParameter("email"))
                .userRole(UserRole.getByName(request.getParameter("userRole")))
                .userStatus(UserStatus.NEWCOMER)
                .build();
        userService.update(user);
        response.sendRedirect("userlist");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Long.parseLong(request.getParameter("userId"));
        userService.delete(userId);
        response.sendRedirect("userlist");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!checkPassword(password, userService.findByLogin(username).getPassword())) {
            request.setAttribute("ERROR", "Invalid email or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
            dispatcher.forward(request, response);
        } else if (userService.findByLogin(username).getUserRole() == UserRole.ADMIN) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/list");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
            rd.forward(request, response);
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        currentUser = null;
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
        rd.forward(request, response);
    }
}
