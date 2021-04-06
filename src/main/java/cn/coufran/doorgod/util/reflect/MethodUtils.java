package cn.coufran.doorgod.util.reflect;

import cn.coufran.doorgod.reflect.ReflectException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodUtils {
    public static <V> V invoke(Method method, Object entity, Object... args) {
        try {
            return (V) method.invoke(entity, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }
}
