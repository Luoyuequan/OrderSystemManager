package cn.luoyuequan.dao.impl;

import cn.luoyuequan.dao.interf.Catelog;
import cn.luoyuequan.model.CatelogModel;
import cn.luoyuequan.util.JDBC;

import java.util.List;
import java.util.Map;

public class CatelogImpl implements Catelog {
    /**
     * 查询类型是否已存在， 唯一性
     *
     * @param catelogModel 类别信息集
     */
    @Override
    public Object queryCheckExist(CatelogModel catelogModel) {
        String sql = "select catelog_name from t_catelog where catelog_name = ?";
        return JDBC.executeQuerySingle(sql, catelogModel.getAttributesValues());
    }

    /**
     * 查询类型信息集
     */
    @Override
    public List<Map> queryCatelogInfors() {
        String sql = "select catelog_id,catelog_name,catelog_description from t_catelog where catelog_del = 0";
        return JDBC.excuteQuery(sql);
    }

    /**
     * 查询 指定 类型 where id
     *
     * @param catelogModel 类别信息集
     */
    @Override
    public List<Map> queryCatelogSingle(CatelogModel catelogModel) {
        String sql = "select catelog_id,catelog_name,catelog_description from t_catelog where catelog_id = ?";
        return JDBC.excuteQuery(sql, catelogModel.getAttributesValues());
    }

    /**
     * 添加类型
     *
     * @param catelogModel 类别数据model
     */
    @Override
    public int insertCatelog(CatelogModel catelogModel) {
        String sql = "insert into t_catelog (catelog_name,catelog_description) values (?,?)";
        return JDBC.executeUpdate(sql, catelogModel.getAttributesValues());
    }

    /**
     * 修改类型信息
     *
     * @param catelogModel 类别数据model
     */
    @Override
    public int updateCatelog(CatelogModel catelogModel) {
        String sql = "UPDATE t_catelog SET catelog_name = ?,catelog_description = ? where catelog_id = ?";
        return JDBC.executeUpdate(sql, catelogModel.getAttributesValues());
    }

    /**
     * 查询回收站
     */
    @Override
    public List<Map> queryRecycleInfors() {
        String sql = "select catelog_id,catelog_name,catelog_description from t_catelog where catelog_del = 1";
        return JDBC.excuteQuery(sql);
    }

    /**
     * 将指定类型放入回收站
     *
     * @param catelogModel 类别数据model
     */
    @Override
    public int deleteCatelog(CatelogModel catelogModel) {
        String sql = "UPDATE t_catelog SET catelog_del = 1 where catelog_id = ?";
        return JDBC.executeUpdate(sql, catelogModel.getAttributesValues());
    }

    /**
     * 清空回收站 将字段catelog_del 为 1  记录删除
     */
    @Override
    public int deleteRecycle() {
        String sql = "delete from t_catelog where catelog_del = 1";
        return JDBC.executeUpdate(sql);
    }

    /**
     * 恢复回收站 指定 类型 将字段catelog_del 改为 0
     *
     * @param catelogModel 类型数据model
     */
    @Override
    public int restoreCatelog(CatelogModel catelogModel) {
        String sql = "UPDATE t_catelog SET catelog_del = 0 where catelog_id = ?";
        return JDBC.executeUpdate(sql, catelogModel.getAttributesValues());
    }
}
