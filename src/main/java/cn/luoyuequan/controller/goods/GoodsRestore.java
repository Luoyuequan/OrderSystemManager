package cn.luoyuequan.controller.goods;

import cn.luoyuequan.model.GoodsModel;
import cn.luoyuequan.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class GoodsRestore extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String[]> datas = req.getParameterMap();
        GoodsModel goodsModel = new GoodsModel();
        for (Map.Entry<String, String[]> entry : datas.entrySet()) {
            goodsModel.setAttributes(entry.getKey(), entry.getValue()[0]);
        }
        GoodsService goodsService = new GoodsService();
        String returnData = goodsService.restoreGoods(goodsModel);
        resp.getWriter().print(returnData);
    }
}
