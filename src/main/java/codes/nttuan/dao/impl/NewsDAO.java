package codes.nttuan.dao.impl;

import codes.nttuan.dao.INewsDAO;
import codes.nttuan.mapper.NewsMapper;
import codes.nttuan.models.NewsModel;

import javax.inject.Inject;
import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

    @Override
    public List<NewsModel> findAll() {
        String sql = "SELECT * FROM NEWS";
        return query(sql, new NewsMapper());
    }

    @Override
    public List<NewsModel> findByCategoryCode(String code) {
        String sql = "SELECT * FROM NEWS n INNER JOIN CATEGORY ct ON n.categoryId = ct.ID WHERE ct.CODE = ?";
        return query(sql, new NewsMapper(), code);
    }

    @Override
    public NewsModel findById(Long id) {
        String sql = "SELECT * FROM NEWS WHERE ID = ?";
        return query(sql, new NewsMapper(), id).get(0);
    }
}
