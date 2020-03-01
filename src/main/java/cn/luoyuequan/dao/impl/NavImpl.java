package cn.luoyuequan.dao.impl;

import cn.luoyuequan.dao.interf.Nav;
import cn.luoyuequan.util.JDBC;

import java.util.List;
import java.util.Map;

public class NavImpl implements Nav {
    @Override
    public List<Map> getNavList() {
        String sql = "select" +
                " nav_1.nav_1_text,nav_2.nav_2_text,nav_2.link_url " +
                "from" +
                " order_system.t_nav_1 as nav_1 left join order_system.t_nav_2 as nav_2 " +
                "on" +
                " nav_1.nav_1_id = nav_2.nav_1_id " +
                "order by nav_1.nav_1_id,nav_2.nav_2_id";
        return JDBC.excuteQuery(sql);
    }
}
