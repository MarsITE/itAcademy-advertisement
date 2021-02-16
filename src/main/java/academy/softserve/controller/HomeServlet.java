package academy.softserve.controller;

import academy.softserve.model.Advert;
import academy.softserve.model.library.AdvertGenre;
import academy.softserve.service.AdvertService;

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
    List<Advert> adverts;
    Advert advert;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String advertId = request.getParameter("advertId");
        if (advertId != null){
            advertService.update(setAdvert(request));
        } else {
            advertService.save(setAdvert(request));
        }
        request.setAttribute("genres", AdvertGenre.values());
        request.setAttribute("adverts", advertService.findAll());
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String advertId = request.getParameter("advertId");
        request.setAttribute("genres", AdvertGenre.values());
        request.setAttribute("adverts", advertService.findAll());
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }



    private Advert setAdvert(HttpServletRequest request){
       advert = new Advert();
        return advert.builder()
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .publishingDate(LocalDate.parse(request.getParameter("publishingDate")))
                .endingDate(LocalDate.parse(request.getParameter("endingDate")))
                .advertGenre(AdvertGenre.valueOf(request.getParameter("advertGenre")))
                .build();
    }



}
