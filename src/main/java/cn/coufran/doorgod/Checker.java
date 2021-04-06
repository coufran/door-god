package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.CustomDecider;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.*;
import cn.coufran.doorgod.reflect.ClassMeta;
import cn.coufran.doorgod.reflect.ClassScanner;
import cn.coufran.doorgod.reflect.MethodMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class Checker {
    private static final String MESSAGE_DEFAULT = "未通过校验";

    private static ClassScanner classScanner = ClassScanner.getInstance();
    private static Executor executor = SimpleExecutor.getInstance();

    public static <T> void check(CustomDecider decider) {
        check(decider, MESSAGE_DEFAULT);
    }

    public static <T> void check(CustomDecider decider, String message) {
        check(null, decider, message);
    }

    public static <T> void check(T value, Decider decider) {
        check(value, decider, MESSAGE_DEFAULT);
    }

    public static <T> void check(T value, Decider decider, String message) {
        executor.execute(value, decider, new StringMessage(message));
    }

    public static <T, R> void check(T entity, SerializableFunction<T, R> getMethod, Decider decider) {
        R value = getMethod.apply(entity);

        MessageTemplate messageTemplate = MessageTemplateFactory.createMessageTemplate(decider);
        Message message = new GetterFunctionAndValueTemplateMessage(messageTemplate)
                .setGetterFunction(getMethod)
                .setValue(value);

        executor.execute(value, decider, message);
    }

    public static <T> void check(T entity) {
        // 扫描类结构
        ClassMeta classMeta = classScanner.scan(entity.getClass());

        List<MethodMeta> methodMetas = classMeta.getMethodMetas();
        for(MethodMeta methodMeta : methodMetas) {
            Method method = methodMeta.getMethod();
            Object value;
            try {
                value = method.invoke(entity);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
            List<Class<? extends Decider>> deciders = methodMeta.getDeciderClasses();
            for(Class<? extends Decider> deciderClass : deciders) {
                Decider decider = null;
                try {
                    decider = deciderClass.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new IllegalStateException(e);
                }
                MessageTemplate messageTemplate = MessageTemplateFactory.createMessageTemplate(decider);
                Message message = new GetterMethodAndValueTemplateMessage(messageTemplate)
                        .setGetterMethod(method)
                        .setValue(value);
                executor.execute(value, decider, message);
            }
        }
    }
}
