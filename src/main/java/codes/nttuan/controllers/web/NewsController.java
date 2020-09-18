package codes.nttuan.controllers.web;

import codes.nttuan.models.NewsModel;
import codes.nttuan.service.INewsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/news/*")
public class NewsController extends HttpServlet {
    @Inject
    private INewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsModel newsById = newsService.findNewsById(req, resp);
        System.out.println(newsById);
        if(newsById != null){
            req.setAttribute("news", newsById);
            req.getRequestDispatcher("/views/web/news.jsp").forward(req, resp);
        } else {
            resp.sendError(404);
        }
    }
}
