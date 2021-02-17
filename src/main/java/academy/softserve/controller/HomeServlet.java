package academy.softserve.controller;

import academy.softserve.model.Advert;
import academy.softserve.model.User;
import academy.softserve.model.library.AdvertGenre;
import academy.softserve.model.library.UserRole;
import academy.softserve.model.library.UserStatus;
import academy.softserve.service.AdvertService;
import academy.softserve.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    private final AdvertService advertService = new AdvertService();
    private final UserService userService = new UserService();
    List<Advert> adverts;
    Advert advert;
    List<User> users;
    User user = User.builder().id(1).firstName("Andrii").lastName("Prybyla").password("1234").dateOfBirth(LocalDate.of(1985, 8, 6)).email("mars@ukr.net").userRole(UserRole.USER).userStatus(UserStatus.NEWCOMER).build();

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
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateAdvert(request, response);
                break;
            case "/advert-info":
                infoAdvert(request, response);
                break;
            default:
                listAdvert(request, response);
                break;
        }
    }

    private void listAdvert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        adverts = advertService.findAll();
        request.setAttribute("adverts", adverts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/advert-list.jsp");
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
                .advertGenre(AdvertGenre.valueOf(request.getParameter("advertGenre")))
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

    private void deleteAdvert(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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


    private void userRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.save(user);
        RequestDispatcher rd = request.getRequestDispatcher("user_list.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("list");
    }

}
