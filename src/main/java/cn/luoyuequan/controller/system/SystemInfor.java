package cn.luoyuequan.controller.system;

import cn.luoyuequan.service.SystemService;
import cn.luoyuequan.util.MapConvertString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SystemInfor extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<Object, Object> systemInfor = new HashMap<>();
        systemInfor.put("CPU", SystemService.getCpuRatioForWindows());
        systemInfor.put("Disk", SystemService.getDisk());
        systemInfor.put("Memory", SystemService.getMemory());
        resp.getWriter().print(MapConvertString.getMapConvertJson(systemInfor));
    }
}
