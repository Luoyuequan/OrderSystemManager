package cn.luoyuequan.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/order_system?serverTimezone=PRC";
    private static final String USER = "dev";
    private static final String PASSWORD = "dev123456.";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection conn = null;
    private static PreparedStatement pst = null;
    private static ResultSet rst = null;

    private static Connection getConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            return conn;
        }
        try {
            Class.forName(DRIVER);
            // 获取连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * insert update delete SQL语句的执行的统一方法
     *
     * @param sql    SQL语句
     * @param params 参数数组，允许不传，允许不传
     * @return 受影响的行数
     */
    public static int executeUpdate(String sql, Object[]... params) {
        // 受影响的行数
        int affectedLine = 0;

        try {
            // 获得连接
            conn = getConnection();
            conn.setAutoCommit(false);
            // 调用SQL
//            insert 获取返回值 Statement.RETURN_GENERATED_KEYS
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (params.length > 1) {
                for (Object[] param : params) {
                    // 参数赋值
                    for (int i = 0; i < param.length; i++) {
                        pst.setObject(i + 1, param[i]);
                    }
                    pst.addBatch();
                }
                // 批量操作
                affectedLine = pst.executeBatch().length;
            } else {
                // 参数赋值
                if (params.length == 1) {
                    for (int i = 0; i < params[0].length; i++) {
                        pst.setObject(i + 1, params[0][i]);
                    }
                }
                // 非批量操作
                affectedLine = pst.executeUpdate();
            }
            conn.commit();
//            对于批量操作时，使用批处理
//            获取insert添加成功的记录 主键值
//            ResultSet updateKey = pst.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     *
     * @param sql    SQL语句
     * @param params 参数数组，允许不传
     */
    private static void executeQueryRS(String sql, Object[]... params) {
        try {
            // 获得连接
            conn = getConnection();
            // 调用SQL
            pst = conn.prepareStatement(sql);

            for (Object[] param : params) {
                // 参数赋值
                for (int i = 0; i < param.length; i++) {
                    pst.setObject(i + 1, param[i]);
                }
            }
            // 执行
            rst = pst.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * SQL 查询将查询结果：一行一列
     *
     * @param sql    SQL语句
     * @param params 参数数组，允许不传
     * @return 结果集
     */
    public static Object executeQuerySingle(String sql, Object[]... params) {
        Object object = null;
        try {
            // 获得连接
            conn = getConnection();
            // 调用SQL
            pst = conn.prepareStatement(sql);
            // 参数赋值
            for (Object[] param : params) {
                for (int i = 0; i < param.length; i++) {
                    pst.setObject(i + 1, param[i]);
                }
            }
            // 执行
            rst = pst.executeQuery();

            if (rst.next()) {
                object = rst.getObject(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 获取结果集列表
     *
     * @param sql    语句
     * @param params sql语句中的参数数组，允许不传
     * @return 结果集
     */
    public static List<Map> excuteQuery(String sql, Object[]... params) {
        // 执行SQL获取结果集
        executeQueryRS(sql, params);

        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;

        // 结果集列数
        int columnCount = 0;
        try {
//            结果集的结构信息
            rsmd = rst.getMetaData();

            // 获得结构的字段列数
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        // 创建List
        List<Map> list = new ArrayList<>();

        try {
            // 将ResultSet的结果保存到List中
//            遍历结果集
            while (rst.next()) {
                Map<String, Object> map = new HashMap<>();
//                单条结果中 循环装载 字段名=字段值
                for (int i = 1; i <= columnCount; i++) {
//                    获取对应的字段名 index从1开始
                    map.put(rsmd.getColumnLabel(i), rst.getObject(i));
                }
                list.add(map);//每一个map代表一条记录，把所有记录存在list中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭所有资源
            closeAll();
        }
        return list;
    }

    /**
     * 关闭所有资源
     */
    private static void closeAll() {
        try {
            // 关闭结果集对象
            if (rst != null) {
                rst.close();
            }

            // 关闭PreparedStatement对象
            if (pst != null) {
                pst.close();
            }

            // 关闭Connection 对象
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
