package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;

import java.lang.reflect.InvocationTargetException;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class CheckExecutor {
    public void execute(Object value, Class<? extends Decider> deciderClass) {
        Decider decider;
        try {
            decider = deciderClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
        Checker.check(value, decider);
    }
}
