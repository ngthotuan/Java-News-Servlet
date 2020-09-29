package codes.nttuan.controllers.admin.api;

import codes.nttuan.constant.SystemConstant;
import codes.nttuan.models.NewsModel;
import codes.nttuan.models.UserModel;
import codes.nttuan.service.INewsService;
import codes.nttuan.utils.HttpUtil;
import codes.nttuan.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/api/admin/news")
public class NewsApi extends HttpServlet {
    @Inject
    private INewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsModel> newsModelList = newsService.findAll();
        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getOutputStream(), newsModelList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //get data form client
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);

        newsModel.setCreatedBy(((UserModel) SessionUtil.getSession().getValue(req, SystemConstant.USER_MODEL)).getUsername());
        //save to db
        newsModel = newsService.save(newsModel);

        // sent back to client
        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getOutputStream(), newsModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newsModel.setModifiedBy(((UserModel) SessionUtil.getSession().getValue(req, SystemConstant.USER_MODEL)).getUsername());
        newsModel = newsService.update(newsModel);
        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getOutputStream(), newsModel);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        System.out.println(newsModel);
        //update db
        newsService.delete(newsModel.getIds());
        // sent back to client
        resp.setContentType("application/json");
        new ObjectMapper().writeValue(resp.getOutputStream(), "{}");
    }
}
