package codes.nttuan.service;

import codes.nttuan.models.NewsModel;
import codes.nttuan.paging.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface INewsService {
    List<NewsModel> findAll();
    List<NewsModel> findAll(Pageable pageable);
    List<NewsModel> findNewsByCategoryCode(HttpServletRequest req, HttpServletResponse res);
    NewsModel findNewsById(HttpServletRequest req, HttpServletResponse res);
    NewsModel save(NewsModel model);
    NewsModel update(NewsModel model);
    void delete(long modelId);
    void delete(long [] modelIds);
    int getTotalItems();
    NewsModel findOne(Long id);
}
