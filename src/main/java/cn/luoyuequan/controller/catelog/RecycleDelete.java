package cn.luoyuequan.controller.catelog;

import cn.luoyuequan.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecycleDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        CatelogService catelogService = new CatelogService();
        String returnData = catelogService.deleteRecycle();
        resp.getWriter().print(returnData);
    }
}
