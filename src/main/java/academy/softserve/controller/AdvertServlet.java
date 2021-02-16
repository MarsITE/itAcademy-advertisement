package academy.softserve.controller;

import academy.softserve.model.library.AdvertGenre;
import academy.softserve.service.AdvertService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/advert")
public class AdvertServlet extends HttpServlet {

    private static final String CREATE = "create";
    private static final String UPDATE = "update";

    private static AdvertService advertService = new AdvertService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException, ServletException {
        String advertId = request.getParameter("advertId");
        String action = request.getParameter("action");
        request.setAttribute("genre", AdvertGenre.values());

        if (CREATE.equals(action)){
            request.getRequestDispatcher("/WEB-INF/pages/advert.jsp").forward(request, resp);
            return;
        }
        if (advertId != null){
            request.setAttribute("advert", advertService.findById(Long.parseLong(advertId)));
            if (UPDATE.equals(action)){
                request.getRequestDispatcher("/WEB-INF/pages/advert.jsp").forward(request, resp);
            } else {
                request.getRequestDispatcher("/WEB-INF/pages/advert_info.jsp").forward(request, resp);
            }
            return;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String advertId = request.getParameter("advertId");
        advertService.delete(Long.parseLong(advertId));
    }

}
