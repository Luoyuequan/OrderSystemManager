package cn.luoyuequan.dao.interf;

import cn.luoyuequan.model.UserModel;

import java.util.List;
import java.util.Map;

public interface User {
    Object queryUsername(UserModel userModel, String identity);

    Object queryCheckExist(UserModel userModel, String identity, String columnName);

    Object queryPasswd(UserModel userModel, String identity);

    List<Map> queryUserInfors(UserModel userModel, String identity);

    <overload>
    List<Map> queryUserInfors(UserModel userModel);

    int insertUser(UserModel userModel, String identity);

    int updateUser(UserModel userModel, String identity);

    int deleteUser(UserModel userModel, String identity);
}
