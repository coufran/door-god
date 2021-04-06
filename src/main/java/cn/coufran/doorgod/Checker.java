package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.CustomDecider;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.GetterFunctionAndValueTemplateMessage;
import cn.coufran.doorgod.message.GetterMethodAndValueTemplateMessage;
import cn.coufran.doorgod.message.Message;
import cn.coufran.doorgod.message.StringMessage;
import cn.coufran.doorgod.reflect.ClassConstruct;
import cn.coufran.doorgod.reflect.ClassScanner;
import cn.coufran.doorgod.reflect.MethodConstruct;

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

    private static Executor executor = SimpleExecutor.getInstance();

    private static ClassScanner classScanner = new ClassScanner();

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

        String messageTemplate = MessageTemplateFactory.createMessageTemplate(decider);
        Message message = new GetterFunctionAndValueTemplateMessage(messageTemplate)
                .setGetterFunction(getMethod)
                .setValue(value);

        executor.execute(value, decider, message);
    }

    public static <T> void check(T entity) {
        ClassConstruct classConstruct = classScanner.scan(entity.getClass());
        List<MethodConstruct> methodConstructs = classConstruct.getMethodConstructs();
        for(MethodConstruct methodConstruct : methodConstructs) {
            Method method = methodConstruct.getMethodMeta();
            Object value;
            try {
                value = method.invoke(entity);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
            List<Class<? extends Decider>> deciders = methodConstruct.getDeciders();
            for(Class<? extends Decider> deciderClass : deciders) {
                Decider decider = null;
                try {
                    decider = deciderClass.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                String messageTemplate = MessageTemplateFactory.createMessageTemplate(decider);
                Message message = new GetterMethodAndValueTemplateMessage(messageTemplate)
                        .setGetterMethod(method)
                        .setValue(value);
                executor.execute(value, decider, message);
            }
        }
    }
}
