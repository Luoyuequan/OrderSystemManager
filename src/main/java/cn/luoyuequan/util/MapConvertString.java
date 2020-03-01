package cn.luoyuequan.util;

import java.util.List;
import java.util.Map;

public class MapConvertString {
    /**
     * 将Map数据 转换为 json格式的字符串
     *
     * @param data 待转换的Map数据
     * @return jsonString json格式的字符串
     */
    public static String getMapConvertJson(Map<Object, Object> data) {
        StringBuilder jsonString = new StringBuilder("{");
        for (Object key : data.keySet()) {
            String tmp = "\"" + key + "\"" + ":" +
                    (
                            data.get(key) instanceof Map ?
                                    getMapConvertJson((Map<Object, Object>) data.get(key)) :
                                    (
                                            (data.get(key) instanceof List) ?
                                                    "[" + String.join(",", (List) data.get(key)) + "]" :
                                                    "\"" + data.get(key) + "\""
                                    )
                    );
            jsonString.append(tmp).append(",");
        }
        int lastIndex = jsonString.lastIndexOf(",");
        jsonString.delete(lastIndex, lastIndex + 1);
        jsonString.append("}");
        return jsonString.toString();
    }

}
