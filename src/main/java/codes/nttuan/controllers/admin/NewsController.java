package codes.nttuan.controllers.admin;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.models.NewsModel;
import codes.nttuan.paging.PageRequest;
import codes.nttuan.paging.Pageable;
import codes.nttuan.service.INewsService;
import codes.nttuan.sorting.Sorter;
import codes.nttuan.utils.FormUtil;

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
        NewsModel model = FormUtil.toModel(NewsModel.class, req);
        Pageable pageable = new PageRequest(model.getCurrentPage(), model.getLimitItems(),
                new Sorter(model.getSortBy(), model.getSortType()));
        model.setListResult(newsService.findAll(pageable));
        model.setTotalPages((int)Math.ceil(1.0*newsService.getTotalItems() / model.getLimitItems()));
        req.setAttribute(SystemConstant.MODEL, model);
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/news/list.jsp");
        rd.forward(req, resp);
    }
}