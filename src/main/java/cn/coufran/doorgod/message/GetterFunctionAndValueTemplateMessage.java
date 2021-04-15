package cn.coufran.doorgod.message;

import cn.coufran.doorgod.SerializableFunction;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * 使用getter方法和值的模版消息
 * <p>
 * 用法如下：
 * <pre>
 * Message message = new GetterFunctionAndValueTemplateMessage("${fieldName}:${value}未通过校验")
 *                       .setGetterFunction(xxx)
 *                       .setValue(1);
 * </pre>
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class GetterFunctionAndValueTemplateMessage extends MethodNameAndValueTemplateMessage {
    /** getter方法 */
    private SerializableFunction getterFunction;

    /**
     * 构造模版消息
     * @param messageTemplate 消息模版
     */
    public GetterFunctionAndValueTemplateMessage(MessageTemplate messageTemplate) {
        super(messageTemplate);
    }

    /**
     * 设置getter方法
     * @param getterFunction getter方法
     * @return 当前对象
     * @param <T> 对象类型
     * @param <R> getter方法返回值类型
     */
    public <T, R> GetterFunctionAndValueTemplateMessage setGetterFunction(SerializableFunction<T, R> getterFunction) {
        this.getterFunction = getterFunction;
        return this;
    }

    /**
     * 获取getter方法
     * @return getter方法
     */
    public SerializableFunction getGetterFunction() {
        return getterFunction;
    }

    /**
     * 通过getter方法获取方法名
     */
    @Override
    public String getMethodName() {
        SerializableFunction getterMethod = getGetterFunction();
        String methodName = null;
        try {
            Method method = getterMethod.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(getterMethod);
            methodName = serializedLambda.getImplMethodName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return methodName;
    }
}
