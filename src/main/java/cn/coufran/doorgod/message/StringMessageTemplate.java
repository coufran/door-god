package cn.coufran.doorgod.message;

import java.util.Map;

/**
 * 字符串模版，模版中使用${xxx}定义空位
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringMessageTemplate extends MessageTemplate {
    /** 模版 */
    private String template;

    /**
     * 指定模版的构造方法
     * @param template 模版
     */
    public StringMessageTemplate(String template) {
        this.template = template;
    }

    /**
     * 获取模版
     * @return 模版
     */
    public String getTemplate() {
        return template;
    }

    /**
     * 构造消息字符串
     */
    @Override
    protected String buildMessage(Map<String, Object> data) {
        String message = getTemplate();
        for(Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String valueString = value == null ? "null" : value.toString();
            message = message.replaceAll("\\$\\{"+key+"\\}", valueString);
        }
        return message;
    }
}
