package codes.nttuan.mapper;

import codes.nttuan.models.NewsModel;
import codes.nttuan.models.RoleModel;
import codes.nttuan.models.UserModel;

import javax.management.relation.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IRowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet resultSet) {
        UserModel userModel = new UserModel();
        try {
            userModel.setId(resultSet.getLong("id"));
            userModel.setUsername(resultSet.getString("username"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setFullName(resultSet.getString("fullName"));
            userModel.setStatus(resultSet.getInt("status"));
            userModel.setRoleId(resultSet.getLong("roleId"));
            try{
                RoleModel role = new RoleModel();
                role.setName(resultSet.getString("name"));
                role.setCode(resultSet.getString("code"));
                userModel.setRole(role);
            } catch (Exception e){
                System.err.println(e.getMessage());
            }
            userModel.setCreatedDate(resultSet.getTimestamp("createdDate"));
            userModel.setCreatedBy(resultSet.getString("createdBy"));
            userModel.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
            userModel.setModifiedBy(resultSet.getString("modifiedBy"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModel;
    }
}
