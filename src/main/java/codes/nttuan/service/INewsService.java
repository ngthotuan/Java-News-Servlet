package codes.nttuan.service;

import codes.nttuan.models.NewsModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface INewsService {
    List<NewsModel> findAll();
    List<NewsModel> findNewsByCategoryCode(HttpServletRequest req, HttpServletResponse res);
    NewsModel findNewsById(HttpServletRequest req, HttpServletResponse res);
}
