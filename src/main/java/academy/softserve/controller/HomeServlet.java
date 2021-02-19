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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static academy.softserve.controller.LoginUtil.*;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    private final AdvertServiceImpl advertService = new AdvertServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();
    List<Advert> adverts;
    Advert advert;
    List<User> users;

    User user;

    @Override
    public void init() throws ServletException {
        servletContext = getServletContext();
    }

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
            default:
                listAdvert(request, response);
                break;
        }
    }

    private void listAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        user = userService.findByLogin(request.getParameter("username"));
        request.setAttribute("user", user);
        adverts = advertService.findAll();
        request.setAttribute("adverts", adverts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list.jsp");
        dispatcher.forward(request, response);
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        users = userService.findAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                .author(user)
                .build();
        advertService.save(advert);
        response.sendRedirect("list");
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
        response.sendRedirect("list");
    }

    private void deleteAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long advertId = Long.parseLong(request.getParameter("advertId"));
        advertService.delete(advertId);
        response.sendRedirect("list");
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

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        dispatcher(request, response, rd);
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
                .userRole(UserRole.USER)
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

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashedPassword = hashPassword(password);

        if (checkPassword(password, hashedPassword)) {
            user = userService.findByLogin(username);
            response.sendRedirect("userlist");
        } else {
            response.getWriter().write("error: Invalid login or password!");
        }
    }

    @Override
    public void destroy() {
        servletContext = null;
    }
}
