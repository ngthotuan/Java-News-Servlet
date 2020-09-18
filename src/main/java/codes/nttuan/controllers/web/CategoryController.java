package codes.nttuan.controllers.web;

import codes.nttuan.models.CategoryModel;
import codes.nttuan.models.NewsModel;
import codes.nttuan.service.ICategoryService;
import codes.nttuan.service.INewsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category/*")
public class CategoryController extends HttpServlet {

    @Inject
    private INewsService newsService;
    @Inject
    private ICategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryModel> categories = categoryService.findAll();
        List<NewsModel> newsByCategoryCode = newsService.findNewsByCategoryCode(req, resp);

        req.setAttribute("categories", categories);
        req.setAttribute("newsByCategory", newsByCategoryCode);

        req.getRequestDispatcher("/views/web/category.jsp").forward(req, resp);

    }
}
