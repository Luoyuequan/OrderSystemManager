package cn.luoyuequan.controller.user;

import cn.luoyuequan.model.UserModel;
import cn.luoyuequan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AdminAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String[]> datas = req.getParameterMap();
        UserModel userModel = new UserModel();
        for (Map.Entry<String, String[]> entry : datas.entrySet()) {
            userModel.setAttributes(entry.getKey(), entry.getValue()[0]);
        }
        UserService userService = new UserService(null, req);
        String returnData = userService.adminInsert(userModel);
        resp.getWriter().print(returnData);
    }
}
