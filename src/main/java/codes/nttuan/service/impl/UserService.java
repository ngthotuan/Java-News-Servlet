package codes.nttuan.service.impl;

import codes.nttuan.dao.IUserDAO;
import codes.nttuan.models.UserModel;
import codes.nttuan.service.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel findOne(String username, String password) {
        return userDAO.findOne(username, password);
    }
}
