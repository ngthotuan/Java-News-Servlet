package codes.nttuan.dao.impl;

import codes.nttuan.dao.INewsDAO;
import codes.nttuan.mapper.NewsMapper;
import codes.nttuan.models.NewsModel;

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
    public NewsModel findOne(Long id) {
        String sql = "SELECT * FROM NEWS WHERE ID = ?";
        List<NewsModel> newsModelList = query(sql, new NewsMapper(), id);
        return newsModelList.isEmpty() ? null : newsModelList.get(0);
    }

    @Override
    public Long save(NewsModel model) {
        String sql = "INSERT INTO NEWS(title, thumbnail, shortDescription, content, categoryId, " +
                "createdDate, createdBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return insert(sql, model.getTitle(), model.getThumbnail(), model.getShortDescription(),
                model.getContent(), model.getCategoryId(), model.getCreatedDate(), model.getCreatedBy());
    }

    @Override
    public boolean update(NewsModel model) {
        String sql = "UPDATE NEWS SET " +
                "title = ?, thumbnail = ?, shortDescription = ?, content = ?, categoryId = ?, " +
                "createdDate = ?, createdBy = ?, modifiedDate = ?, modifiedBy = ? " +
                "WHERE id = ?";
        return update(sql, model.getTitle(), model.getThumbnail(), model.getShortDescription(),
                model.getContent(), model.getCategoryId(), model.getCreatedDate(), model.getCreatedBy(),
                model.getModifiedDate(), model.getModifiedBy(), model.getId());
    }

    @Override
    public boolean delete(long modelId) {
        String sql = "DELETE FROM NEWS WHERE id = ?";
        return update(sql, modelId);
    }

    @Override
    public List<NewsModel> find(int offset, int limit) {
        String sql = "SELECT * FROM NEWS LIMIT ?, ?";
        return query(sql, new NewsMapper(), offset, limit);
    }
}
