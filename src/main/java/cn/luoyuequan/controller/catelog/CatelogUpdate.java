package cn.luoyuequan.controller.catelog;

import cn.luoyuequan.model.CatelogModel;
import cn.luoyuequan.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CatelogUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String[]> datas = req.getParameterMap();
        CatelogModel catelogModel = new CatelogModel();
        for (Map.Entry<String, String[]> entry : datas.entrySet()) {
            catelogModel.setAttributes(entry.getKey(), entry.getValue()[0]);
        }
        CatelogService catelogService = new CatelogService();
        String returnData = catelogService.updateCatelog(catelogModel);
        resp.getWriter().print(returnData);
    }
}
