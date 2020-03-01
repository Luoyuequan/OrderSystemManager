package cn.luoyuequan.dao.impl;

import cn.luoyuequan.dao.interf.User;
import cn.luoyuequan.model.UserModel;
import cn.luoyuequan.util.JDBC;

import java.util.List;
import java.util.Map;

public class UserImpl implements User {
    /**
     * 查询会员
     *
     * @param userModel 会员数据集
     * @param identity  身份
     * @return 查询结果集
     */
    @Override
    public List<Map> queryUserInfors(UserModel userModel, String identity) {
        String sql = "select " +
                "user_id,user_name,user_pw,user_realname,user_address" +
                " from t_user";
        return JDBC.excuteQuery(sql);
    }

    /**
     * 查询管理员 排除自己
     *
     * @param userModel 管理员数据集
     * @return 查询结果集
     */
    @Override
    public List<Map> queryUserInfors(UserModel userModel) {
        String sql = "select " +
                "user_id,user_name,user_pw" +
                " from t_admin where user_name != ?";
        return JDBC.excuteQuery(sql, userModel.getAttributesValues());
    }

    /**
     * 查询用户账号
     *
     * @param userModel 用户数据集
     * @return 查询结果
     */
    @Override
    public Object queryUsername(UserModel userModel, String identity) {
        String sql = "select " +
                "user_name" +
                " from " + identity +
                " where user_name = ?";
        return JDBC.executeQuerySingle(sql, new Object[]{userModel.getAttributesValue("user_name")});
    }

    /**
     * 查询指定列 是否存在某值
     *
     * @param identity   用户身份
     * @param columnName 查询列名
     * @param userModel  用户数据集
     */
    @Override
    public Object queryCheckExist(UserModel userModel, String identity, String columnName) {
        String sql = "select " +
                columnName +
                " from " + identity +
                " where " + columnName + " = ?";
        return JDBC.executeQuerySingle(sql, userModel.getAttributesValues());
    }

    /**
     * 查询用户密码 通过账号查询
     *
     * @param userModel 用户数据表
     * @return 查询结果
     */
    @Override
    public Object queryPasswd(UserModel userModel, String identity) {
        String sql = "select " +
                "user_pw" +
                " from " + identity +
                " where user_name = ?";
        return JDBC.executeQuerySingle(sql, new Object[]{userModel.getAttributesValue("user_name")});
    }

    /**
     * 添加用户
     *
     * @param userModel 用户数据集
     * @param identity  用户身份 （admin,user）
     * @return 影响行数
     */
    @Override
    public int insertUser(UserModel userModel, String identity) {
        String sql = "insert into " + identity + " (user_pw,user_name)" +
                " values " +
                "(?,?)";
        return JDBC.executeUpdate(sql, userModel.getAttributesValues());
    }

    /**
     * 修改用户信息
     *
     * @param userModel 用户数据集
     * @return 影响行数
     */
    @Override
    public int updateUser(UserModel userModel, String identity) {
        String sql = "UPDATE " + identity + " SET " +
                "user_pw = ?" +
                " where " +
                "user_name = ?";
        return JDBC.executeUpdate(sql, userModel.getAttributesValues());
    }

    /**
     * 删除指定用户 删除条件id
     *
     * @param userModel 用户数据集
     * @param identity  被删除的用户 身份 （admin，user）
     * @return 影响行数
     */
    @Override
    public int deleteUser(UserModel userModel, String identity) {
        String sql = "delete from " + identity + " where user_id = ?";
        return JDBC.executeUpdate(sql, userModel.getAttributesValues());
    }
}
