package codes.nttuan.dao;

import codes.nttuan.models.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
    UserModel findOne(String username, String password);
}
