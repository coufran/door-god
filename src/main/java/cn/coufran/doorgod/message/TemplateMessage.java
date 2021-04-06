package cn.coufran.doorgod.message;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     模版消息。<br/>
 *     模版消息可根据数据的不同，生成不同的消息。<br/>
 *     模版中留下若干空白，与数据部分整合，生成最终的消息。
 * </p>
 * <p>
 *     用法如下：
 *     <pre>
 * Message message = new TemplateMessage("我是错误消息，${key}")
 *                       .put("key", "value");
 *     </pre>
 * </p>
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class TemplateMessage extends Message {
    /** 消息模版，待替换部分使用${key}指定 */
    private String template;
    /** 数据 */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 通过消息模版构造模版消息，消息模版不得为null
     * @param template 消息模版
     */
    public TemplateMessage(String template) {
        this.template = template;
    }

    /**
     * 设置数据
     * @param key 对应消息模版中的${key}
     * @param value 消息值，使用该值替换${key}
     * @return 当前对象，用于链式调用
     */
    public TemplateMessage set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * 获取数据
     * @return 数据
     */
    protected Map<String, Object> getData() {
        return data;
    }

    /**
     * 使用数据替换消息模版中的空白区域，生成最终的消息串
     */
    public String asString() {
        String message = template;
        Map<String, Object> data = getData();
        for(Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String valueString = value == null ? "null" : value.toString();
            message = message.replaceAll("\\$\\{"+key+"\\}", valueString);
        }
        return message;
    }
}
