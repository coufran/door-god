package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassScanner {

    public ClassConstruct scan(Class<?> classMeta) {
        ClassConstruct classConstruct = new ClassConstruct(classMeta);
        List<MethodConstruct> methodConstructs = this.scanMethods(classMeta);
        classConstruct.setMethodConstructs(methodConstructs);

        return classConstruct;
    }

    private List<MethodConstruct> scanMethods(Class<?> classMeta) {
        Method[] methods = classMeta.getMethods();
        List<MethodConstruct> methodConstructs = Arrays.stream(methods)
                .map(this::scanMethod)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return methodConstructs;
    }

    private MethodConstruct scanMethod(Method method) {
        Field field = getFieldByMethod(method);
        if(field == null) {
            return null;
        }

        MethodConstruct methodConstruct = new MethodConstruct(method);

        Annotation[] methodAnnotations = method.getAnnotations();
        List<Class<? extends Decider>> methodDeciders = this.scanDecider(methodAnnotations);
        methodConstruct.addDeciders(methodDeciders);

        Annotation[] fieldAnnotations = field.getAnnotations();
        List<Class<? extends Decider>> fieldDeciders = this.scanDecider(fieldAnnotations);
        methodConstruct.addDeciders(fieldDeciders);

        return methodConstruct;
    }

    private List<Class<? extends Decider>> scanDecider(Annotation[] annotations) {
        List<Class<? extends Decider>> deciders = new ArrayList<>(annotations.length);
        for(Annotation annotation : annotations) {
            Class<? extends Annotation> annotationClass = annotation.annotationType();
            Decide decideAnnotation = annotationClass.getAnnotation(Decide.class);
            if(decideAnnotation == null) {
                continue;
            }
            Class<? extends Decider> deciderClass = decideAnnotation.decideBy();
            deciders.add(deciderClass);
        }
        return deciders;
    }

    private Field getFieldByMethod(Method method) {
        int methodParameterCount = method.getParameterCount();
        Class<?> methodReturnType = method.getReturnType();
        if(methodParameterCount != 0 || methodReturnType.equals(Void.class)) { // 不是getter
            return null;
        }

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
        if(methodNameChars[0] != '$' && methodNameChars[0] != '_') {
            methodNameChars[0] = (char) (methodNameChars[0] - ('A' - 'a'));
        }
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
