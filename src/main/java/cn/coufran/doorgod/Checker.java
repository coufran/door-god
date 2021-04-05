package cn.coufran.doorgod;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class Checker {
    public static <T> void check(CustomDecider decider) {
        check(decider, "未通过校验");
    }

    public static <T> void check(CustomDecider decider, String message) {
        check(null, decider, message);
    }

    public static <T> void check(T value, Decider decider) {
        check(value, decider, "未通过校验");
    }

    public static <T> void check(T value, Decider decider, String message) {
        if(!decider.decide(value)) {
            throw new ValidateException(message);
        }
    }

    public static <T, R> void check(T entity, SerializableFunction<T, R> getMethod, Decider decider) {
        R value = getMethod.apply(entity);

        MessageBuilder messageBuilder = MessageBuilderFactory.createMessageBuilder(decider);
        String message = messageBuilder.builderMessage(entity, getMethod);

        check(value, decider, message);
    }
}
