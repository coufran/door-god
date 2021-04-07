package cn.coufran.doorgod.reflect.util;

import cn.coufran.doorgod.reflect.ReflectException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodUtils {
    @SuppressWarnings("unchecked")
    public static <V> V invoke(Method method, Object entity, Object... args) {
        try {
            return (V) method.invoke(entity, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectException(e);
        }
    }

    public static boolean isGetter(Method method) {
        if(method.getParameterCount() != 0 || method.getReturnType().equals(Void.class)) {
            return false;
        }
        String methodName = method.getName();
        if("getClass".equals(methodName)) {
            return false;
        }
        return methodName.matches("(get|is)[A-Z$_].*");
    }

    public static String getFieldNameByGetter(Method method) {
        return MethodUtils.getFieldNameByGetterName(method.getName());
    }

    public static String getFieldNameByGetterName(String getterName) {
        if(getterName.startsWith("get")) {
            getterName = getterName.replaceFirst("get", "");
        } else if(getterName.startsWith("is")) {
            getterName = getterName.replaceFirst("is", "");
        } else { // 不是getter
            throw new ReflectException(getterName + "() is not a getter");
        }
        char[] chars = getterName.toCharArray();
        chars[0] = (char) (chars[0] - ('A' - 'a'));
        return new String(chars);
    }

    public static String getSetterNameByPropertyName(String propertyName) {
        char[] chars = propertyName.toCharArray();
        chars[0] = (char) (chars[0] + ('A' - 'a')); // 首字母转换为大写
        return "set" + new String(chars);
    }
}
