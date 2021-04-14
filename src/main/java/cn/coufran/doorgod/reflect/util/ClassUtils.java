package cn.coufran.doorgod.reflect.util;

import cn.coufran.doorgod.reflect.ReflectException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Field getField(Class<?> clazz, String fieldName) {
        if(clazz == null) {
            return null;
        }

        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
        }
        if(field == null) {
            field = getField(clazz.getSuperclass(), fieldName);
        }
        return field;
    }

    public static List<Method> getMethods(Class<?> clazz, String methodName) {
        Method[] methods = clazz.getMethods();
        return Arrays.stream(methods)
                .filter(method -> method.getName().equals(methodName))
                .collect(Collectors.toList());
    }

    public static Method getMethod(Class<?> clazz, String methodName) {
        List<Method> methods = getMethods(clazz, methodName);
        if(methods.isEmpty()) {
            return null;
        }
        return methods.get(0);
    }

}
