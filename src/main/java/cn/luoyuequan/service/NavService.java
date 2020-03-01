package cn.luoyuequan.service;

import cn.luoyuequan.dao.impl.NavImpl;
import cn.luoyuequan.dao.interf.Nav;
import cn.luoyuequan.util.MapConvertString;

import java.util.*;

public class NavService {
    public List<Object> getNavList() {
        Nav navImpl = new NavImpl();
        List<Map> navList = navImpl.getNavList();

        HashMap<Object, Object> tmpDatas = new LinkedHashMap<>(16);

        ArrayList<Object> nav_2 = new ArrayList<>();
        List<Object> returnDatas = new ArrayList<>();
        for (Map data : navList
        ) {
            HashMap<Object, Object> tmp = new LinkedHashMap<>(16);
            Object nav2Text = data.get("nav_2_text");
            Object linkUrl = data.get("link_url");
            Object nav1Text = data.get("nav_1_text");
            tmp.put("text", nav2Text == null || "".equals(nav2Text) ? "无" : nav2Text);
            tmp.put("url", linkUrl == null || "".equals(linkUrl) ? "" : linkUrl);
            if (tmpDatas.get(nav1Text) == null) {
                if (tmpDatas.size() > 0) {
                    returnDatas.add(MapConvertString.getMapConvertJson(tmpDatas));
                    tmpDatas.clear();
                }
                nav_2.clear();
                nav_2.add(MapConvertString.getMapConvertJson(tmp));
                tmpDatas.put(nav1Text, nav_2.clone());
            } else {
                Object obj = nav1Text;
                List<Object> list = (List<Object>) tmpDatas.get(obj);
                list.add(MapConvertString.getMapConvertJson(tmp));
            }
//            System.out.println(tmpDatas);
        }

        return returnDatas;
    }
}
