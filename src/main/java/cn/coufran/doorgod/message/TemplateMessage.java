package cn.coufran.doorgod.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class TemplateMessage extends Message {
    private String template;
    private Map<String, Object> data = new HashMap<>();

    public TemplateMessage(String template) {
        this.template = template;
    }

    public TemplateMessage set(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    protected Map<String, Object> getData() {
        return data;
    }

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
