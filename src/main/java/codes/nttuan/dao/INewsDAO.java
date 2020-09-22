package codes.nttuan.dao;

import codes.nttuan.models.NewsModel;

import java.util.List;

public interface INewsDAO {
    List<NewsModel> findAll();
    List<NewsModel> findByCategoryCode(String code);
    NewsModel findOne(Long id);
    Long save(NewsModel model);
    boolean update(NewsModel model);
    boolean delete(long modelId);
}
