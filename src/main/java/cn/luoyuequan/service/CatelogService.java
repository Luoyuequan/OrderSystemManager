package cn.luoyuequan.service;

import cn.luoyuequan.dao.impl.CatelogImpl;
import cn.luoyuequan.dao.interf.Catelog;
import cn.luoyuequan.model.CatelogModel;
import cn.luoyuequan.util.MapConvertString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatelogService {
    private Catelog catelogImpl;

    public CatelogService() {
        this.catelogImpl = new CatelogImpl();
    }

    /**
     * 查询类型列表
     *
     * @return 类型列表数据 json格式字符串
     */
    public List<String> catelogList() {
        List<Map> catelogList = this.catelogImpl.queryCatelogInfors();
        List<String> returnDatas = new ArrayList<>();
        for (Map data : catelogList) {
            returnDatas.add(MapConvertString.getMapConvertJson(data));
        }
        return returnDatas;
    }

    /**
     * 查询 指定 类型
     *
     * @param catelogModel 类别信息集
     */
    public List<String> catelogSingle(CatelogModel catelogModel) {
        List<Map> catelogList = this.catelogImpl.queryCatelogSingle(catelogModel);
        List<String> returnDatas = new ArrayList<>();
        for (Map data : catelogList) {
            returnDatas.add(MapConvertString.getMapConvertJson(data));
        }
        return returnDatas;
    }

    /**
     * 添加类型时，检查类型是否已存在，唯一性
     *
     * @param catelogModel 类别信息集
     * @return 检查结果 提示信息json格式字符串
     */
    public String queryCheckExist(CatelogModel catelogModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        Object catelogNameExist = this.catelogImpl.queryCheckExist(catelogModel);
        if (catelogNameExist == null) {
            noticeMsg.put("state", true);
            noticeMsg.put("name","category_name");
            noticeMsg.put("msg", "类型不存在!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "类型已存在!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 添加类型
     *
     * @param catelogModel 类别数据model
     * @return 添加结果 提示信息json格式字符串
     */
    public String insertCatelog(CatelogModel catelogModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int insertResult = this.catelogImpl.insertCatelog(catelogModel);
        if (insertResult > 0) {
            noticeMsg.put("state", true);
            noticeMsg.put("msg", "添加成功!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "添加失败!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 修改类型信息
     *
     * @param catelogModel 类别数据model
     * @return 修改结果 提示信息json格式字符串
     */
    public String updateCatelog(CatelogModel catelogModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int updateResult = this.catelogImpl.updateCatelog(catelogModel);
        if (updateResult > 0) {
            noticeMsg.put("state", true);
            noticeMsg.put("msg", "修改成功!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "修改失败,请重新尝试!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 将指定类型放入回收站
     *
     * @param catelogModel 类别数据model
     * @return 修改结果 提示信息json格式字符串
     */
    public String deleteCatelog(CatelogModel catelogModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int deleteResult = this.catelogImpl.deleteCatelog(catelogModel);
        if (deleteResult > 0) {
            noticeMsg.put("state", true);
            noticeMsg.put("msg", "删除成功，可从回收站恢复!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "删除失败，请重新尝试!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 查询回收站列表
     *
     * @return 查询结果 json格式字符串列表
     */
    public List<String> recycleList() {
        List<Map> recycleList = this.catelogImpl.queryRecycleInfors();
        List<String> returnDatas = new ArrayList<>();
        for (Map data : recycleList) {
            returnDatas.add(MapConvertString.getMapConvertJson(data));
        }
        return returnDatas;
    }

    /**
     * 清空回收站列表
     *
     * @return 清空结果 提示信息json格式字符串
     */
    public String deleteRecycle() {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int deleteResult = this.catelogImpl.deleteRecycle();
        if (deleteResult > 0) {
            noticeMsg.put("state", true);
            noticeMsg.put("msg", "清空成功!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "清空失败，请重新尝试!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 恢复回收站内的 指定类型
     *
     * @return 恢复结果 提示信息json格式字符串
     */
    public String restoreCatelog(CatelogModel catelogModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int restoreResult = this.catelogImpl.restoreCatelog(catelogModel);
        if (restoreResult > 0) {
            noticeMsg.put("state", true);
            noticeMsg.put("msg", "恢复成功!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "恢复失败，请重新尝试!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }
}
