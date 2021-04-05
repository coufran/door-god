package cn.coufran.doorgod;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author liuhm8
 * @since 1.0.0
 * @version 1.0.0
 */
public class MessageBuilder {
    private String messageTemplate;

    public MessageBuilder(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public <T, R> String builderMessage(T entity, SerializableFunction<T, R> getMethod) {
        R value = getMethod.apply(entity);
        String methodName = null;
        try {
            Method method = getMethod.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(getMethod);
            methodName = serializedLambda.getImplMethodName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buildMessageWithMethodName(methodName, value);
    }

    public <T> String builderMessage(T entity, Method getMethod) {
        Object value = null;
        try {
            value = getMethod.invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String methodName = getMethod.getName();
        return buildMessageWithMethodName(methodName, value);
    }

    private <R> String buildMessageWithMethodName(String methodName, R value) {
        String fieldName = null;
        if(methodName.startsWith("is")) {
            fieldName = methodName.replaceFirst("is", "");
        } else if(methodName.startsWith("get")) {
            fieldName = methodName.replaceFirst("get", "");
        } else {
            throw new IllegalArgumentException("method" + methodName + " is not a getter");
        }
        char[] cs = fieldName.toCharArray();
        cs[0] = (char) (cs[0] - ('A' - 'a'));
        fieldName = new String(cs);
        return buildMessageWithFieldName(fieldName, value);
    }

    private <R> String buildMessageWithFieldName(String fieldName, R value) {
        return messageTemplate.replaceAll("\\$\\{fieldName\\}", fieldName)
                .replaceAll("\\$\\{value\\}", value==null?"null":value.toString());
    }
}
