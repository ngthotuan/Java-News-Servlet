package codes.nttuan.dao.impl;

import codes.nttuan.dao.GenericDAO;
import codes.nttuan.mapper.IRowMapper;

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

    @Override
    public boolean update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isUpdated = true;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement, params);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            isUpdated = false;
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdated;
    }

    @Override
    public Long insert(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            setParams(preparedStatement, params);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getLong(1);
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public int count(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int total = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParams(preparedStatement, params);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                total = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total;
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
                }  else if (params[i] instanceof Timestamp) {
                    preparedStatement.setTimestamp(i + 1, (Timestamp) params[i]);
                }  else if (params[i] == null) {
                    preparedStatement.setNull(i + 1, Types.NULL);
                }
            } catch (SQLException e){
                System.err.println("set params error");
            }
        }
    }
}
