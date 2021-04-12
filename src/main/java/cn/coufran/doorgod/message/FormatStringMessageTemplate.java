package cn.coufran.doorgod.message;

import java.util.Map;

/**
 * 字符串模版，模版中使用%s定义空位，使用参数指定空位名
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class FormatStringMessageTemplate extends StringMessageTemplate {
    /** 模版的模版 */
    private String formatTemplate;
    /** 模版的参数 */
    private String[] args;

    /**
     * 指定模版的构造方法
     * @param formatTemplate 模版的模版
     * @param args 模版的参数，不需要添加${}，只需要中间的Key值
     */
    public FormatStringMessageTemplate(String formatTemplate, String... args) {
        super(null);
        this.formatTemplate = formatTemplate;
        this.args = args;
    }

    /**
     * 获取模版
     * @return 模版
     */
    public String getTemplate() {
        for(int i=0; i<args.length; i++) {
            args[i] = "${" + args[i] + "}";
        }
        return String.format(formatTemplate, args);
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
