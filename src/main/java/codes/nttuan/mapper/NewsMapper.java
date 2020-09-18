package codes.nttuan.mapper;

import codes.nttuan.models.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements IRowMapper<NewsModel> {
    @Override
    public NewsModel mapRow(ResultSet resultSet) {
        NewsModel news = new NewsModel();
        try {
            news.setId(resultSet.getLong("id"));
            news.setTitle(resultSet.getString("title"));
            news.setThumbnail(resultSet.getString("thumbnail"));
            news.setShortDescription(resultSet.getString("shortDescription"));
            news.setContent(resultSet.getString("content"));
            news.setCategoryId(resultSet.getLong("categoryId"));
            news.setCreatedDate(resultSet.getTimestamp("createdDate"));
            news.setCreatedBy(resultSet.getString("createdBy"));
            news.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
            news.setModifiedBy(resultSet.getString("modifiedBy"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }
}
