package cn.coufran.doorgod.message;

import cn.coufran.doorgod.SerializableFunction;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class GetterFunctionAndValueTemplateMessage extends MethodNameAndValueTemplateMessage {
    private SerializableFunction getterFunction;

    public GetterFunctionAndValueTemplateMessage(String template) {
        super(template);
    }

    public <T, R> GetterFunctionAndValueTemplateMessage setGetterFunction(SerializableFunction<T, R> getterFunction) {
        this.getterFunction = getterFunction;
        return this;
    }

    public SerializableFunction getGetterFunction() {
        return getterFunction;
    }

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
