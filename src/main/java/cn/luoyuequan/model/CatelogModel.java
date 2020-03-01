package cn.luoyuequan.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CatelogModel {
    //    LinkedHashMap 按照添加顺序 排队
    private Map<String, Object> attributes = new LinkedHashMap<>();

    /**
     * 设置指定key 和 value 用于批量设置
     */
    public void setAttributes(String key, Object value) {
        this.attributes.put(key, value);
    }

    /**
     * 获取Map的全部
     */
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    /**
     * 获取map的全部value值
     */
    public Object[] getAttributesValues() {
        List<Object> values = new ArrayList<>();
        for (Map.Entry<String, Object> entry : attributes.entrySet()
        ) {
            values.add(entry.getValue());
        }
        return values.toArray();
    }

    /**
     * 获取指定key 对应的 value 值
     */
    public Object getAttributesValue(String key) {
        return this.attributes.get(key);
    }
}
