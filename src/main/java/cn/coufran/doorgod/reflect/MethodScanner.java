package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodScanner implements Scanner<Method> {
    private static final MethodScanner INSTANCE = new MethodScanner();

    private FieldScanner fieldScanner = FieldScanner.getInstance();

    private MethodScanner() {
    }

    public static MethodScanner getInstance() {
        return INSTANCE;
    }

    @Override
    public MethodMeta scan(Method method) {
        if(!isGetter(method)) {
            return null;
        }
        // 构造元数据
        MethodMeta methodMeta = new MethodMeta(method);
        // 扫描Annotation
        Annotation[] annotations = method.getAnnotations();
        List<Decider> deciders = this.parseDecider(annotations);
        methodMeta.addDeciders(deciders);
        // 扫描字段
        Field field = getField(method);
        Decidable fieldDecidable = fieldScanner.scan(field);
        methodMeta.addDeciders(fieldDecidable.getDeciders());

        return methodMeta;
    }

    private boolean isGetter(Method method) {
        if(method.getParameterCount() != 0 || method.getReturnType().equals(Void.class)) {
            return false;
        }
        String methodName = method.getName();
        if("getClass".equals(methodName)) {
            return false;
        }
        return methodName.matches("(get|is)[A-Z$_].*");
    }

    private Field getField(Method method) {
        // 解析fieldName
        String methodName = method.getName();
        if(methodName.startsWith("get")) {
            methodName = methodName.replaceFirst("get", "");
        } else if(methodName.startsWith("is")) {
            methodName = methodName.replaceFirst("is", "");
        } else { // 不是getter
            return null;
        }
        char[] methodNameChars = methodName.toCharArray();
        methodNameChars[0] = (char) (methodNameChars[0] - ('A' - 'a'));
        String fieldName = new String(methodNameChars);

        // 查找field
        Class<?> clazz = method.getDeclaringClass();
        Field field = getField(clazz, fieldName);
        return field;
    }

    private Field getField(Class<?> clazz, String fieldName) {
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
}
