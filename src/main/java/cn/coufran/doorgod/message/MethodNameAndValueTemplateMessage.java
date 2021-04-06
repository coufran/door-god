package cn.coufran.doorgod.message;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodNameAndValueTemplateMessage extends FieldNameAndValueTemplateMessage {
    private String methodName;

    public MethodNameAndValueTemplateMessage(String template) {
        super(template);
    }

    public MethodNameAndValueTemplateMessage setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

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
