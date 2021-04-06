package cn.coufran.doorgod.message;

/**
 * 包含字段名和值的模版消息，使用getter方法名生成字段名
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodNameAndValueTemplateMessage extends FieldNameAndValueTemplateMessage {
    /** getter方法名 */
    private String methodName;

    /**
     * 构造模版消息
     * @param messageTemplate 消息模版，至少包含{@link FieldNameAndValueTemplateMessage#KEY_FIELD$NAME}
     *                 和{@link FieldNameAndValueTemplateMessage#KEY_VALUE}两个空白
     */
    public MethodNameAndValueTemplateMessage(MessageTemplate messageTemplate) {
        super(messageTemplate);
    }

    /**
     * 设置getter方法名
     * @param methodName getter方法名
     * @return 当前对象
     */
    public MethodNameAndValueTemplateMessage setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    /**
     * 获取getter方法名
     * @return getter方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 通过getter方法名生成字段名<br/>
     * getter需要是标准getter，即方法名以get或is开始的，其后紧跟字段名，使用驼峰风格
     */
    @Override
    public String getFieldName() {
        String fieldName = getMethodName();
        if(fieldName.startsWith("get")) {
            fieldName = fieldName.replaceFirst("get", "");
        } else if(fieldName.startsWith("is")) {
            fieldName = fieldName.replaceFirst("is", "");
        }
        char[] fieldNameChars = fieldName.toCharArray();
        if(fieldNameChars[0] >= 'A' && fieldNameChars[0] <= 'Z') {
            fieldNameChars[0] = (char) (fieldNameChars[0] - ('A' - 'a'));
            fieldName = new String(fieldNameChars);
        }
        return fieldName;
    }
}
