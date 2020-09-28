package codes.nttuan.controllers.web;

import codes.nttuan.models.CategoryModel;
import codes.nttuan.models.NewsModel;
import codes.nttuan.service.ICategoryService;
import codes.nttuan.service.INewsService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    @Inject
    private ICategoryService categoryService;
    @Inject
    private INewsService newsService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CategoryModel> categories = categoryService.findAll();
        List<NewsModel> listNews = newsService.findAll();

        req.setAttribute("categories", categories);
        req.setAttribute("listNews", listNews);

        RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(req, resp);
    }
}
