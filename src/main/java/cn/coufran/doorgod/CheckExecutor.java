package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class CheckExecutor {
    public <T> void execute(T entity, Object value, Class<? extends Decider> deciderClass, Method method) {
        Decider decider;
        try {
            decider = deciderClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }

        MessageBuilder messageBuilder = MessageBuilderFactory.createMessageBuilder(decider);
        String message = messageBuilder.builderMessage(entity, method);

        Checker.check(value, decider, message);
    }
}
