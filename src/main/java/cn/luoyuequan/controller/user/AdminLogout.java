package cn.luoyuequan.controller.user;

import cn.luoyuequan.util.MapConvertString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminLogout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(true);
        Map<Object, Object> msgData = new HashMap<>();
        if (session.getAttribute(session.getId()) != null) {
            session.invalidate();
            msgData.put("state", true);
            msgData.put("msg", "注销成功!<br>3秒后自动跳转到登录界面...");
        } else {
            msgData.put("state", false);
            msgData.put("msg", "用户身份不存在!");
        }
        resp.getWriter().print(MapConvertString.getMapConvertJson(msgData));
    }
}
