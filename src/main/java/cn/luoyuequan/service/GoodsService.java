package cn.luoyuequan.service;

import cn.luoyuequan.dao.impl.GoodsImpl;
import cn.luoyuequan.dao.interf.Goods;
import cn.luoyuequan.model.GoodsModel;
import cn.luoyuequan.util.MapConvertString;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsService {
    private Goods goodsImpl;

    public GoodsService() {
        this.goodsImpl = new GoodsImpl();
    }

    /**
     * 查询菜品列表
     *
     * @return 菜品列表数据 json格式字符串
     */
    public List<String> goodsList() {
        List<Map> goodsList = this.goodsImpl.queryGoodsInfors();
        List<String> returnDatas = new ArrayList<>();
        for (Map data : goodsList) {
            returnDatas.add(MapConvertString.getMapConvertJson(data));
        }
        return returnDatas;
    }

    /**
     * 查询 指定 菜品
     *
     * @param goodsModel 菜品信息集
     */
    public List<String> goodsSingle(GoodsModel goodsModel) {
        List<Map> goodsList = this.goodsImpl.queryGoodsSingle(goodsModel);
        List<String> returnDatas = new ArrayList<>();
        for (Map data : goodsList) {
            returnDatas.add(MapConvertString.getMapConvertJson(data));
        }
        return returnDatas;
    }

    /**
     * 添加菜品名称时，检查名称是否已存在，唯一性
     *
     * @param goodsModel 菜品名称信息集
     * @return 检查结果 提示信息json格式字符串
     */
    public String queryCheckExist(GoodsModel goodsModel, String columnName) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        Object goodsNameExist = this.goodsImpl.queryCheckExist(goodsModel);
        if (goodsNameExist == null) {
            noticeMsg.put("state", true);
            noticeMsg.put("name", columnName);
            noticeMsg.put("msg", "菜品名称不存在!");
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "菜品名称已存在!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 添加菜品
     *
     * @param goodsModel 菜品数据model
     * @return 添加结果 提示信息json格式字符串
     */
    public String insertGoods(GoodsModel goodsModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int insertResult = this.goodsImpl.insertGoods(goodsModel);
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
     * 修改菜品信息 并 删除
     *
     * @param goodsModel 菜品数据model
     * @return 修改结果 提示信息json格式字符串
     */
    public String updateGoods(GoodsModel goodsModel, String filePath) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        List<Map> oldGoodsData = this.goodsImpl.queryGoodsSingle(goodsModel);
        int updateResult = this.goodsImpl.updateGoods(goodsModel);
        if (updateResult > 0) {
            if (
                    goodsModel.getAttributesValue("goods_img").equals(oldGoodsData.get(0).get("goods_img"))
            ) {
                noticeMsg.put("state", true);
                noticeMsg.put("msg", "修改成功!");
            } else {
                File oldImgFile = new File(filePath + oldGoodsData.get(0).get("goods_img"));
                System.out.println(oldImgFile.getAbsolutePath());
                if (oldImgFile.delete()) {
                    noticeMsg.put("state", true);
                    noticeMsg.put("msg", "修改成功!");
                } else {
                    noticeMsg.put("state", true);
                    noticeMsg.put("msg", "修改成功,旧图片删除失败,请手动删除!");
                }
            }

        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "修改失败,请重新尝试!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 将指定菜品放入回收站
     *
     * @param goodsModel 菜品数据model
     * @return 修改结果 提示信息json格式字符串
     */
    public String deleteGoods(GoodsModel goodsModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int deleteResult = this.goodsImpl.deleteGoods(goodsModel);
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
        List<Map> recycleList = this.goodsImpl.queryRecycleInfors();
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
    public String deleteRecycle(String filePath) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        List<Map> deleteData = this.goodsImpl.queryRecycleInfors();
        int deleteResult = this.goodsImpl.deleteRecycle();
        if (deleteResult > 0) {
            boolean deleteImgsBool = true;
            for (Map data : deleteData) {
                File deleteImgFile = new File(filePath + data.get("img"));
//                deleteImgFile.getAbsolutePath()
                if (!deleteImgFile.delete()) {
                    deleteImgsBool = false;
                }
            }
            if (deleteImgsBool) {
                noticeMsg.put("state", true);
                noticeMsg.put("msg", "清空成功!");
            } else {
                noticeMsg.put("state", true);
                noticeMsg.put("msg", "清空成功,图片未全部删除,请手动删除!");
            }
        } else {
            noticeMsg.put("state", false);
            noticeMsg.put("msg", "清空失败，请重新尝试!");
        }
        return MapConvertString.getMapConvertJson(noticeMsg);
    }

    /**
     * 恢复回收站内的 指定菜品
     *
     * @return 恢复结果 提示信息json格式字符串
     */
    public String restoreGoods(GoodsModel goodsModel) {
        Map<Object, Object> noticeMsg = new HashMap<>();
        int restoreResult = this.goodsImpl.restoreGoods(goodsModel);
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
