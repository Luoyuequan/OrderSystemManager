package cn.luoyuequan.service;

import cn.luoyuequan.dao.impl.UserImpl;
import cn.luoyuequan.dao.interf.User;
import cn.luoyuequan.model.UserModel;
import cn.luoyuequan.util.MapConvertString;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class UserService {
    private String identity;
    private HttpSession session;

    /**
     * 构造函数
     *
     * @param identity 用户身份
     * @param request  客户端请求对象
     */
    public UserService(String identity, HttpServletRequest request) {
        // 如果不存在 session 会话，则创建一个 session 对象
        this.session = request.getSession(true);
        this.identity = identity == null ? (String) this.session.getAttribute("identity") : identity;
    }

    /**
     * 管理员信息列表
     */
    public List<String> adminList() {
        User userImpl = new UserImpl();
        UserModel userModel = new UserModel();
        userModel.setAttributes("user_name", session.getAttribute(session.getId()));
        List<Map> adminList = userImpl.queryUserInfors(userModel);
        List<String> returnDatas = new ArrayList<>();
        for (Map data : adminList) {
            returnDatas.add(MapConvertString.getMapConvertJson(data));
        }
        return returnDatas;
    }

    /**
     * 管理员添加
     */
    public String adminInsert(UserModel userModel) {
        User userImpl = new UserImpl();
        Map<Object, Object> noticeMsg = new HashMap<>();
        int insertResult = userImpl.insertUser(userModel, identity);
        if (insertResult > 0) {
            noticeMsg.put("state", true);
            noticeMsg.put("msg", "添加成功!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "添加失败!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 删除指定管理员
     */
    public String adminDelete(UserModel userModel) {
        User userImpl = new UserImpl();
        Map<Object, Object> noticeMsg = new HashMap<>();
        int deleteResult = userImpl.deleteUser(userModel, identity);
        if (deleteResult > 0) {
            noticeMsg.put("state", true);
            noticeMsg.put("msg", "删除成功!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "删除失败!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 管理员修改自身密码
     *
     * @param newUserModel 修改后的新数据集
     * @param oldUserMoel  修改前的原数据集
     * @return 修改结果提示字符串
     */
    public String adminUpdate(UserModel oldUserMoel, UserModel newUserModel) {
        User userImpl = new UserImpl();
        Object oldPasswd = userImpl.queryPasswd(oldUserMoel, identity);
        boolean passwdCheck = oldPasswd.equals(oldUserMoel.getAttributesValue("user_pw"));
        Map<Object, Object> noticeMsg = new HashMap<>();
        if (passwdCheck) {
            int updateResult = userImpl.updateUser(newUserModel, identity);
            if (updateResult > 0 && delSession()) {
                noticeMsg.put("state", true);
                noticeMsg.put("msg", "修改成功!<br>3秒后自动跳转到登录界面...");
            } else {
                noticeMsg.put("state", false);
                noticeMsg.put("msg", "修改失败,请重新操作!");
            }
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "原密码错误!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 登录验证账号 密码
     *
     * @param userModel 用户数据集
     * @return 验证结果json格式字符
     */
    public String loginCheck(UserModel userModel) {
        User userImpl = new UserImpl();
        Map<Object, Object> noticeMsg = new HashMap<>();
        Object usernameExist = userImpl.queryUsername(userModel, identity);
        //判断账号存在性
        if (usernameExist == null) {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "账号不存在!");
        } else {
            Object pw = userModel.getAttributesValue("user_pw");
            if (pw == null || "".equals(pw)){
                noticeMsg.put("state", false);
                noticeMsg.put("msg", "密码不能为空!");
                return MapConvertString.getMapConvertJson(noticeMsg);
            }
            Object passwd = userImpl.queryPasswd(userModel, identity);
            boolean passwdCheck = Objects.equals(passwd,pw);
            //判断密码正确性
            if (passwdCheck) {
                if (setSession(userModel)) {
                    noticeMsg.put("state", true);
                    noticeMsg.put("msg", "登录成功!");
//                    noticeMsg.put("user_name", userModel.getAttributesValue("user_name"));
                } else {
                    noticeMsg.put("state", false);
                    noticeMsg.put("msg", "账号已登录,请勿重复登录!");
                }
            } else {
                noticeMsg.put("state", false);
                noticeMsg.put("msg", "密码错误!");
            }
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 查询某条记录的单个字段信息
     *
     * @param userModel 用户数据集
     * @return json格式的字符串
     */
    public String checkInfor(UserModel userModel, String columnName) {
        User userImpl = new UserImpl();
        Object checkExist = userImpl.queryCheckExist(userModel, identity, columnName);
        Map<Object, Object> returnData = new HashMap<>();
        if (checkExist == null) {
            returnData.put("state", true);
            returnData.put("name",columnName);
            returnData.put("msg", "信息不存在");
        } else {
            returnData.put("state", false);
            returnData.put("msg", "信息已存在");
        }
        return MapConvertString.getMapConvertJson(returnData);
    }

    /**
     * 登录成功后 设置回话 添加登录状态和用户身份
     */
    private boolean setSession(UserModel userModel) {
        String user_name = userModel.getAttributesValue("user_name").toString();
        if (session.getAttribute(session.getId()) == null) {
            session.setAttribute("identity", identity);
            session.setAttribute(session.getId(), user_name);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 账号密码修改成功后 删除回话
     */
    private boolean delSession() {
        if (session.getAttribute(session.getId()) != null) {
            session.invalidate();
            return true;
        } else {
            return false;
        }
    }
}
