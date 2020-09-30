package codes.nttuan.controllers.admin;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.service.ICategoryService;
import codes.nttuan.service.INewsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/news/edit")
public class NewsControllerEdit extends HttpServlet {
    @Inject
    private ICategoryService categoryService;

    @Inject
    private INewsService newsService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(SystemConstant.CATEGORY_MODEL, categoryService.findAll());
        String id = req.getParameter("id");
        if(id != null){
            try {
                req.setAttribute(SystemConstant.NEWS_MODEL, newsService.findOne(Long.parseLong(id)));
            } catch (NumberFormatException ignored){}
        }
        req.getRequestDispatcher("/views/admin/news/edit.jsp").forward(req, resp);
    }
}
