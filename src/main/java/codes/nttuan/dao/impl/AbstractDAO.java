package codes.nttuan.dao.impl;

import codes.nttuan.dao.GenericDAO;
import codes.nttuan.mapper.CategoryMapper;
import codes.nttuan.mapper.IRowMapper;
import codes.nttuan.models.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T>{
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/NEWS";
            String username = "root";
            String password = "123456";
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.printf("Connect database error!!\nMessage %s", e.getMessage());
            return null;
        }
    }


    @Override
    public List<T> query(String sql, IRowMapper<T> mapper, Object... params) {
        List<T> res = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if(connection != null){
            try {
                preparedStatement = connection.prepareStatement(sql);
                // params
                setParams(preparedStatement, params);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    res.add(mapper.mapRow(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try{
                    connection.close();
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e){
                    System.err.println("close connection fail!!!");
                }
            }
        }
        return res;
    }

    private void setParams(PreparedStatement preparedStatement, Object... params) {
        for(int i = 0; i < params.length; i++){
            try {
                if (params[i] instanceof Integer) {
                    preparedStatement.setInt(i + 1, (Integer) params[i]);
                } else if (params[i] instanceof Long) {
                    preparedStatement.setLong(i + 1, (Long) params[i]);
                } else if (params[i] instanceof String) {
                    preparedStatement.setString(i + 1, (String) params[i]);
                }
            } catch (SQLException e){
                System.err.println("set params error");
            }
        }
    }
}
