package cn.luoyuequan.controller.goods;

import cn.luoyuequan.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecycleDelete extends HttpServlet {
    private String filePath;

    public void init() {
        // 获取文件将被存储的位置
        filePath = getServletContext().getInitParameter("file-upload");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        GoodsService goodsService = new GoodsService();
        String returnData = goodsService.deleteRecycle(filePath);
        resp.getWriter().print(returnData);
    }
}
