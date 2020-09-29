package codes.nttuan.service;

import codes.nttuan.models.UserModel;

public interface IUserService {
    UserModel findOne(String username, String password);
}
