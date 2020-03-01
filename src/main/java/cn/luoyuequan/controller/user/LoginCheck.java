package cn.luoyuequan.controller.user;

import cn.luoyuequan.model.UserModel;
import cn.luoyuequan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginCheck extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String[]> datas = req.getParameterMap();
        UserModel userModel = new UserModel();
        String identity = null;
        for (Map.Entry<String, String[]> entry : datas.entrySet()) {
//            System.out.println(entry.getKey());
            if ("identity".equals(entry.getKey())) {
//                用于区分身份  查询表
                identity = "t_" + entry.getValue()[0];
                continue;
            }
            userModel.setAttributes(entry.getKey(), entry.getValue()[0]);
        }
//        System.out.println(userModel.getAttributes());
//        System.out.println(userModel.getAttributesValues());
        UserService userService = new UserService(identity, req);
        String returnData = userService.loginCheck(userModel);
        resp.getWriter().print(returnData);
    }
}
