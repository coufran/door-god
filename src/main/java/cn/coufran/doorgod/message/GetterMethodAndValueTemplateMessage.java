package cn.coufran.doorgod.message;

import java.lang.reflect.Method;

/**
 * 使用getter方法和值的模版消息
 * <p>
 *     用法如下：
 *     <pre>
 * Message message = new GetterMethodAndValueTemplateMessage("${fieldName}:${value}未通过校验")
 *                       .setGetterMethod(xxx)
 *                       .setValue(1);
 *     </pre>
 * </p>
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class GetterMethodAndValueTemplateMessage extends MethodNameAndValueTemplateMessage {
    /** getter方法 */
    private Method getterMethod;

    /**
     * 构造模版消息
     * @param template 消息模版，至少包含{@link FieldNameAndValueTemplateMessage#KEY_FIELD$NAME}
     *                 和{@link FieldNameAndValueTemplateMessage#KEY_VALUE}两个空白
     */
    public GetterMethodAndValueTemplateMessage(String template) {
        super(template);
    }

    /**
     * 设置getter方法
     * @param getterMethod getter方法
     * @return 当前对象
     */
    public <T, R> GetterMethodAndValueTemplateMessage setGetterMethod(Method getterMethod) {
        this.getterMethod = getterMethod;
        return this;
    }

    /**
     * 获取getter方法
     * @return getter方法
     */
    public Method getGetterMethod() {
        return getterMethod;
    }

    /**
     * 通过getter方法获取方法名
     */
    @Override
    public String getMethodName() {
        return getGetterMethod().getName();
    }
}
