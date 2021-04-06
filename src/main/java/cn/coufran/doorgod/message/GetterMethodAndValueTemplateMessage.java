package cn.coufran.doorgod.message;

import java.lang.reflect.Method;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class GetterMethodAndValueTemplateMessage extends MethodNameAndValueTemplateMessage {
    private Method getterMethod;

    public GetterMethodAndValueTemplateMessage(String template) {
        super(template);
    }

    public <T, R> GetterMethodAndValueTemplateMessage setGetterMethod(Method getterMethod) {
        this.getterMethod = getterMethod;
        return this;
    }

    public Method getGetterMethod() {
        return getterMethod;
    }

    @Override
    public String getMethodName() {
        return getGetterMethod().getName();
    }
}
