package codes.nttuan.mapper;

import codes.nttuan.models.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements IRowMapper<CategoryModel>{

    @Override
    public CategoryModel mapRow(ResultSet resultSet) {
        CategoryModel category = new CategoryModel();
        try {
            category.setId(resultSet.getLong("id"));
            category.setCode(resultSet.getString("code"));
            category.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }
}
