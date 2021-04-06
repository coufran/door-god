package cn.coufran.doorgod.message;

import java.util.Map;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringMessageTemplate extends MessageTemplate {
    private String template;

    public StringMessageTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

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
