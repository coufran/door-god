package cn.coufran.doorgod.util.reflect;

import cn.coufran.doorgod.reflect.ReflectException;

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
public class MethodUtils {
    public static <V> V invoke(Method method, Object entity, Object... args) {
        try {
            return (V) method.invoke(entity, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

    public static String getSetterNameByPropertyName(String propertyName) {
        char[] chars = propertyName.toCharArray();
        chars[0] = (char) (chars[0] + ('A' - 'a')); // 首字母转换为大写
        return "set" + new String(chars);
    }

    public static List<Method> getMethodsByName(Class clazz, String methodName) {
        Method[] methods = clazz.getMethods();
        return Arrays.stream(methods)
                .filter(method -> method.getName().equals(methodName))
                .collect(Collectors.toList());
    }
}
