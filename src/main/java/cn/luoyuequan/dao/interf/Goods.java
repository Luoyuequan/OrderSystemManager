package cn.luoyuequan.dao.interf;

import cn.luoyuequan.model.GoodsModel;

import java.util.List;
import java.util.Map;

public interface Goods {
    /**
     * 查询 菜品名称  存在性  唯一性
     *
     * @param goodsModel 菜品信息集
     */
    Object queryCheckExist(GoodsModel goodsModel);

    /**
     * 查询菜品信息集
     */
    List<Map> queryGoodsInfors();

    /**
     * 查询 指定 菜品 where id
     *
     * @param goodsModel 菜品数据model
     */
    List<Map> queryGoodsSingle(GoodsModel goodsModel);

    /**
     * 添加菜品
     *
     * @param goodsModel 菜品数据model
     */
    int insertGoods(GoodsModel goodsModel);

    /**
     * 修改菜品信息
     *
     * @param goodsModel 菜品数据model
     */
    int updateGoods(GoodsModel goodsModel);

    /**
     * 查询回收站
     */
    List<Map> queryRecycleInfors();

    /**
     * 将指定菜品放入回收站
     *
     * @param goodsModel 菜品数据model
     */
    int deleteGoods(GoodsModel goodsModel);

    /**
     * 清空回收站 将字段goods_del 为 1  记录删除
     */
    int deleteRecycle();

    /**
     * 恢复回收站 指定 菜品 将字段goods_del 改为 0
     *
     * @param goodsModel 菜品数据model
     */
    int restoreGoods(GoodsModel goodsModel);
}
