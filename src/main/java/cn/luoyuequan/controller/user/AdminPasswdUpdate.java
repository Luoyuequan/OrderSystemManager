package cn.luoyuequan.controller.user;

import cn.luoyuequan.model.UserModel;
import cn.luoyuequan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AdminPasswdUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Map<String, String[]> receiveData = req.getParameterMap();
//            用户原先的旧数据
        UserModel oldUserModel = new UserModel();
//            用户修改后的新数据
        UserModel newUserModel = new UserModel();
        for (Map.Entry<String, String[]> entry : receiveData.entrySet()) {
            switch (entry.getKey()) {
                case "user_name":
                    oldUserModel.setAttributes(entry.getKey(), entry.getValue()[0]);
                    newUserModel.setAttributes(entry.getKey(), entry.getValue()[0]);
                    break;
                case "user_pw":
                    oldUserModel.setAttributes(entry.getKey(), entry.getValue()[0]);
                    break;
                case "new_pw":
                    newUserModel.setAttributes("user_pw", entry.getValue()[0]);
                    break;
            }
        }
        UserService userService = new UserService(null, req);
        String returnData = userService.adminUpdate(oldUserModel, newUserModel);
        resp.getWriter().print(returnData);

    }
}
