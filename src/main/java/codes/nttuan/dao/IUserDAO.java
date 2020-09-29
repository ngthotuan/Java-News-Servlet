package codes.nttuan.dao;

import codes.nttuan.models.UserModel;

public interface IUserDAO {
    UserModel findOne(String username, String password);
}
