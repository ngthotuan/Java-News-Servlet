package codes.nttuan.dao;

import codes.nttuan.models.NewsModel;
import codes.nttuan.paging.PageRequest;
import codes.nttuan.paging.Pageable;

import java.util.List;

public interface INewsDAO {
    List<NewsModel> findAll();
    List<NewsModel> findByCategoryCode(String code);
    NewsModel findOne(Long id);
    Long save(NewsModel model);
    boolean update(NewsModel model);
    boolean delete(long modelId);
    List<NewsModel> findAll(Pageable pageable);
    int getTotalItems();
}
