package cn.luoyuequan.controller.user;

import cn.luoyuequan.model.UserModel;
import cn.luoyuequan.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CheckInfor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        Map<String, String[]> datas = req.getParameterMap();
        UserModel userModel = new UserModel();
        String columnName = null;
        for (Map.Entry<String, String[]> entry : datas.entrySet()
        ) {
            columnName = entry.getKey();
            userModel.setAttributes(entry.getKey(),entry.getValue()[0]);
        }
        UserService userService = new UserService(null, req);
        assert columnName != null;
        String returnData = userService.checkInfor(userModel, columnName);
        resp.getWriter().print(returnData);
    }
}
