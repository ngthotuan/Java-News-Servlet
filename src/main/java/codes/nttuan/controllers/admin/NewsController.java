package codes.nttuan.controllers.admin;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.models.NewsModel;
import codes.nttuan.service.INewsService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/news/list")
public class NewsController extends HttpServlet {
    @Inject
    private INewsService newsService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int limitItems = Integer.parseInt(req.getParameter("limitItems"));
        NewsModel model = new NewsModel();
        model.setCurrentPage(currentPage);
        model.setLimitItems(limitItems);
        model.setListResult(newsService.find(currentPage, limitItems));
        model.setTotalPages((int)Math.ceil(1.0*newsService.findAll().size() / limitItems));
        req.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/news/listNews.jsp");
        rd.forward(req, resp);
    }
}