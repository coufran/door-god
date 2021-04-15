package cn.coufran.doorgod.message;

import java.util.HashMap;
import java.util.Map;

/**
 * 模版消息。<br>
 * 模版消息可根据数据的不同，生成不同的消息。<br>
 * 模版中留下若干空白，与数据部分整合，生成最终的消息。
 * <p>
 * 用法如下：
 * <pre>
 * Message message = new TemplateMessage("我是错误消息，${key}")
 *                       .put("key", "value");
 * </pre>
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class TemplateMessage extends Message {
    /** 消息模版，待替换部分使用${key}指定 */
    private MessageTemplate messageTemplate;
    /** 数据 */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 通过消息模版构造模版消息，消息模版不得为null
     * @param messageTemplate 消息模版
     */
    public TemplateMessage(MessageTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public MessageTemplate getTemplate() {
        return messageTemplate;
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
        Map<String, Object> data = getData();
        String message = getTemplate().buildMessage(data);
        return message;
    }
}
