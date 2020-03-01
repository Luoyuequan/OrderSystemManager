package cn.luoyuequan.dao.interf;

import cn.luoyuequan.model.CatelogModel;

import java.util.List;
import java.util.Map;

public interface Catelog {
    /**
     * 查询类型是否已存在， 唯一性
     *
     * @param catelogModel 类别信息集
     */
    Object queryCheckExist(CatelogModel catelogModel);

    /**
     * 查询类型信息集
     */
    List<Map> queryCatelogInfors();

    /**
     * 查询 指定 类型 where id
     */
    List<Map> queryCatelogSingle(CatelogModel catelogModel);

    /**
     * 添加类型
     *
     * @param catelogModel 类别数据model
     */
    int insertCatelog(CatelogModel catelogModel);

    /**
     * 修改类型信息
     *
     * @param catelogModel 类别数据model
     */
    int updateCatelog(CatelogModel catelogModel);

    /**
     * 查询回收站
     */
    List<Map> queryRecycleInfors();

    /**
     * 将指定类型放入回收站
     *
     * @param catelogModel 类别数据model
     */
    int deleteCatelog(CatelogModel catelogModel);

    /**
     * 清空回收站 将字段catelog_del 为 1  记录删除
     */
    int deleteRecycle();

    /**
     * 恢复回收站 指定 类型 将字段catelog_del 改为 0
     *
     * @param catelogModel 类型数据model
     */
    int restoreCatelog(CatelogModel catelogModel);
}
