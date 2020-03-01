package cn.luoyuequan.controller.goods;

import cn.luoyuequan.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodsList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        GoodsService goodsService = new GoodsService();
        List<String> returnData = goodsService.goodsList();
        resp.getWriter().print(returnData);
    }
}
