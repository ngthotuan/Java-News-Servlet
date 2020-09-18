package codes.nttuan.dao;

import codes.nttuan.models.NewsModel;

import java.util.List;

public interface INewsDAO {
    List<NewsModel> findAll();
    List<NewsModel> findByCategoryCode(String code);
    NewsModel findById(Long id);
}
