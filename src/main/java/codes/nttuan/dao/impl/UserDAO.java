package codes.nttuan.dao.impl;

import codes.nttuan.dao.IUserDAO;
import codes.nttuan.mapper.UserMapper;
import codes.nttuan.models.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findOne(String username, String password) {
        String sql = "SELECT * FROM USER INNER JOIN ROLE ON USER.ROLEID = ROLE.ID WHERE username = ? and password = ?";
        List<UserModel> userModelList = query(sql, new UserMapper(), username, password);
        return userModelList.isEmpty() ? null : userModelList.get(0);
    }
}
