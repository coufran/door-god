package cn.coufran.doorgod.util.reflect;

import cn.coufran.doorgod.reflect.ReflectException;

import java.lang.reflect.InvocationTargetException;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassUtils {
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ReflectException(e);
        }
    }
}
