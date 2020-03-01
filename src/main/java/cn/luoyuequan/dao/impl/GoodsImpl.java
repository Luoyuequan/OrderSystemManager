package cn.luoyuequan.dao.impl;

import cn.luoyuequan.dao.interf.Goods;
import cn.luoyuequan.model.GoodsModel;
import cn.luoyuequan.util.JDBC;

import java.util.List;
import java.util.Map;

public class GoodsImpl implements Goods {

    /**
     * 查询 菜品名称  存在性  唯一性
     *
     * @param goodsModel 菜品信息集
     */
    @Override
    public Object queryCheckExist(GoodsModel goodsModel) {
        String sql = "select goods_name from t_goods where goods_name = ?";
        return JDBC.executeQuerySingle(
                sql,
                goodsModel.getAttributesValues()
        );
    }

    /**
     * 查询菜品信息集
     */
    @Override
    public List<Map> queryGoodsInfors() {
        String sql = "select " +
                "g.goods_id as id,g.goods_name as goodsName,g.goods_description as description," +
                "g.goods_img as img,g.goods_market_price as price,c.catelog_name as catelogName,g.goods_renqun as renqun" +
                " from t_goods as g join t_catelog as c " +
                "on g.goods_catelog_id = c.catelog_id and g.goods_del = 0" +
                " order by g.goods_id";
        return JDBC.excuteQuery(sql);
    }

    /**
     * 查询 指定 菜品 where id
     *
     * @param goodsModel 菜品数据model
     */
    @Override
    public List<Map> queryGoodsSingle(GoodsModel goodsModel) {
        String sql = "select " +
                "goods_id,goods_name,goods_description,goods_img,goods_market_price as price,goods_catelog_id,goods_renqun" +
                " from t_goods where goods_id = ?";

        return JDBC.excuteQuery(
                sql,
                new Object[]{goodsModel.getAttributesValue("goods_id")}
        );
    }

    /**
     * 添加菜品
     *
     * @param goodsModel 菜品数据model
     */
    @Override
    public int insertGoods(GoodsModel goodsModel) {
        String sql = "insert into t_goods " +
                "(goods_name,goods_description,goods_img,goods_market_price,goods_catelog_id,goods_renqun)" +
                " values (?,?,?,?,?,?)";
        return JDBC.executeUpdate(
                sql,
                goodsModel.getAttributesValues()
        );
    }

    /**
     * 修改菜品信息
     *
     * @param goodsModel 菜品数据model
     */
    @Override
    public int updateGoods(GoodsModel goodsModel) {
        String sql = "UPDATE t_goods SET " +
                "goods_name = ?,goods_description = ?,goods_img = ?,goods_market_price = ?,goods_catelog_id = ?,goods_renqun = ?" +
                " where goods_id = ?";
        return JDBC.executeUpdate(
                sql,
                goodsModel.getAttributesValues()
        );
    }

    /**
     * 查询回收站
     */
    @Override
    public List<Map> queryRecycleInfors() {
        String sql = "select " +
                "g.goods_id as id,g.goods_name as goodsName,g.goods_description as description," +
                "g.goods_img as img,g.goods_market_price as price,c.catelog_name as catelogName,g.goods_renqun as renqun" +
                " from t_goods as g join t_catelog as c " +
                "on g.goods_catelog_id = c.catelog_id and g.goods_del = 1" +
                " order by g.goods_id";
        return JDBC.excuteQuery(sql);
    }

    /**
     * 将指定菜品放入回收站
     *
     * @param goodsModel 菜品数据model
     */
    @Override
    public int deleteGoods(GoodsModel goodsModel) {
        String sql = "UPDATE t_goods SET goods_del = 1 where goods_id = ?";
        return JDBC.executeUpdate(
                sql,
                goodsModel.getAttributesValues()
        );
    }

    /**
     * 清空回收站 将字段goods_del 为 1  记录删除
     */
    @Override
    public int deleteRecycle() {
        String sql = "delete from t_goods where goods_del = 1";
        return JDBC.executeUpdate(sql);
    }

    /**
     * 恢复回收站 指定 菜品 将字段goods_del 改为 0
     *
     * @param goodsModel 菜品数据model
     */
    @Override
    public int restoreGoods(GoodsModel goodsModel) {
        String sql = "UPDATE t_goods SET goods_del = 0 where goods_id = ?";
        return JDBC.executeUpdate(
                sql,
                goodsModel.getAttributesValues()
        );
    }
}
