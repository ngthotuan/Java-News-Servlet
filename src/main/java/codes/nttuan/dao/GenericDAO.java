package codes.nttuan.dao;

import codes.nttuan.mapper.IRowMapper;

import java.util.List;

public interface GenericDAO<T> {
    List<T> query(String sql, IRowMapper<T> mapper, Object... params);
    boolean update(String sql, Object... params);
    Long insert(String sql, Object... params);
}
