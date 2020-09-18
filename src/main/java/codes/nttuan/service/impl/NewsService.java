package codes.nttuan.service.impl;

import codes.nttuan.dao.INewsDAO;
import codes.nttuan.models.NewsModel;
import codes.nttuan.service.INewsService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class NewsService implements INewsService {
    @Inject
    private INewsDAO newsDAO;
    @Override
    public List<NewsModel> findAll() {
        return newsDAO.findAll();
    }

    @Override
    public List<NewsModel> findNewsByCategoryCode(HttpServletRequest req, HttpServletResponse res) {
        String[] pathParts = req.getPathInfo().split("/");
        System.out.println(pathParts[1]);
        return newsDAO.findByCategoryCode(pathParts[1]);
    }

    @Override
    public NewsModel findNewsById(HttpServletRequest req, HttpServletResponse res) {
        Long id;
        String path = req.getPathInfo();
        try {
            System.out.println(path);
            System.out.println(path.substring(path.lastIndexOf("-")+1));
            id = Long.parseLong(path.substring(path.lastIndexOf("-") + 1));
        } catch (Exception e){
            id = null;
        }
        return newsDAO.findById(id);
    }
}
