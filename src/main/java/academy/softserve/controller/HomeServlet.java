package academy.softserve.controller;

import academy.softserve.configuration.tables.TablesRepository;
import academy.softserve.model.Advert;
import academy.softserve.model.User;
import academy.softserve.model.library.AdvertGenre;
import academy.softserve.model.library.UserRole;
import academy.softserve.model.library.UserStatus;
import academy.softserve.service.AdvertServiceImpl;
import academy.softserve.service.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static academy.softserve.controller.util.ServletUtil.*;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(HomeServlet.class);

    private final AdvertServiceImpl advertService = new AdvertServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();
    private final TablesRepository tablesRepository = new TablesRepository();

    private static final String LOGIN = "username";
    private static final String PASSWORD = "password";
    private static final String USER_ID = "userId";
    private static final String ADVERT_ID = "advertId";
    private static final String ADVERT_GENRE = "advertGenre";

    private User currentUser;

    @Override
    public void init(){
        tablesRepository.createTables();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            doGet(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
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
            case "/listByAuthor":
                listAdvertByAuthorId(request, response);
                break;
            case "/listByAuthorUser":
                listAdvertByAuthorIdUser(request, response);
                break;
            case "/advertGenre":
                listAdvertByGenre(request, response);
                break;
            case "/advertGenreUser":
                listAdvertByGenreUser(request, response);
                break;
            default:
                listAdvertUser(request, response);
                break;
        }
    }

    private void listAdvert(HttpServletRequest request, HttpServletResponse response) {

        if (currentUser == null) {
            currentUser = userService.findByLogin(request.getParameter(LOGIN));
        }
        HttpSession session = request.getSession();

        session.setAttribute("currentUser", currentUser);

        request.setAttribute("adverts", advertService.findAll());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void listAdvertByAuthorId(HttpServletRequest request, HttpServletResponse response) {

        long authorId = Long.parseLong(request.getParameter("authorId"));

        request.setAttribute("advertsById", advertService.findByAuthorId(authorId));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list-byAuthor.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void listAdvertByAuthorIdUser(HttpServletRequest request, HttpServletResponse response) {

        long authorId = Long.parseLong(request.getParameter("authorId"));

        request.setAttribute("advertsById", advertService.findByAuthorId(authorId));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list-byAuthor-user.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void listAdvertByGenre(HttpServletRequest request, HttpServletResponse response) {

        AdvertGenre advertGenre = AdvertGenre.getByName(request.getParameter(ADVERT_GENRE));

        request.setAttribute("advertsByGenre", advertService.findByGenre(advertGenre));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list-byGenre.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void listAdvertByGenreUser(HttpServletRequest request, HttpServletResponse response) {

        AdvertGenre advertGenre = AdvertGenre.getByName(request.getParameter(ADVERT_GENRE));

        request.setAttribute("advertsByGenre", advertService.findByGenre(advertGenre));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list-byGenre-user.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void listAdvertUser(HttpServletRequest request, HttpServletResponse response) {

        if (currentUser == null) {
            currentUser = userService.findByLogin(request.getParameter(LOGIN));
        }
        HttpSession session = request.getSession();

        session.setAttribute("currentUser", currentUser);

        request.setAttribute("adverts", advertService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list-user.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("users", userService.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/user-list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-form.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter(ADVERT_ID));
        Advert existingAdvert = advertService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-form.jsp");
        request.setAttribute("advert", existingAdvert);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }

    }

    private void insertAdvert(HttpServletRequest request, HttpServletResponse response) {
        Advert advert = Advert.builder()
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .publishingDate(convertSqlDateToLocalDate(request.getParameter("publishingDate")))
                .endingDate(convertSqlDateToLocalDate(request.getParameter("endingDate")))
                .advertGenre(AdvertGenre.getByName(request.getParameter(ADVERT_GENRE)))
                .author(currentUser)
                .build();
        advertService.save(advert);

        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void updateAdvert(HttpServletRequest request, HttpServletResponse response) {
        long advertId = Integer.parseInt(request.getParameter(ADVERT_ID));
        Advert advert = Advert.builder().id(advertId)
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .publishingDate(convertSqlDateToLocalDate(request.getParameter("publishingDate")))
                .endingDate(convertSqlDateToLocalDate(request.getParameter("endingDate")))
                .advertGenre(AdvertGenre.getByName(request.getParameter(ADVERT_GENRE)))
                .build();

        advertService.update(advert);

        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void deleteAdvert(HttpServletRequest request, HttpServletResponse response) {
        long advertId = Long.parseLong(request.getParameter(ADVERT_ID));
        advertService.delete(advertId);

        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void infoAdvert(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter(ADVERT_ID));
        Advert existingAdvert = advertService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-info.jsp");
        request.setAttribute("advert", existingAdvert);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }


    private void userRegistration(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/registration-form.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void userEditForm(HttpServletRequest request, HttpServletResponse response) {
        long id = Long.parseLong(request.getParameter(USER_ID));
        User existingUser = userService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/registration-form.jsp");
        request.setAttribute("user", existingUser);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void userSignIn(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/sign-in-form.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) {
        String password = request.getParameter(PASSWORD);
        String hashedPassword = hashPassword(password);

        User user = User.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .password(hashedPassword)
                .dateOfBirth(convertSqlDateToLocalDate(request.getParameter("dateOfBirth")))
                .email(request.getParameter("email"))
                .userRole(UserRole.USER)
                .userStatus(UserStatus.NEWCOMER)
                .build();
        userService.save(user);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/userlist");

        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        long userId = Integer.parseInt(request.getParameter(USER_ID));
        User user = User.builder()
                .id(userId)
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .password(request.getParameter(PASSWORD))
                .dateOfBirth(convertSqlDateToLocalDate(request.getParameter("dateOfBirth")))
                .email(request.getParameter("email"))
                .userRole(UserRole.getByName(request.getParameter("userRole")))
                .userStatus(UserStatus.NEWCOMER)
                .build();
        userService.update(user);

        try {
            response.sendRedirect("userlist");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter(USER_ID));
        userService.delete(userId);

        try {
            response.sendRedirect("userlist");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        if (!checkPassword(password, userService.findByLogin(username).getPassword())) {
            request.setAttribute("ERROR", "Invalid email or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e.getMessage());
            }
        } else if (userService.findByLogin(username).getUserRole() == UserRole.ADMIN) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/list");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e.getMessage());
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void logOut(HttpServletRequest request, HttpServletResponse response) {
        currentUser = null;
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/");

        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }
}
