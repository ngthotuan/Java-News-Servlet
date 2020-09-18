package codes.nttuan.dao.impl;

import codes.nttuan.dao.ICategoryDAO;
import codes.nttuan.mapper.CategoryMapper;
import codes.nttuan.models.CategoryModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM CATEGORY";
        return query(sql, new CategoryMapper());
    }
}
