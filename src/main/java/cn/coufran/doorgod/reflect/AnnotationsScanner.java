package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.reflect.util.ClassUtils;
import cn.coufran.doorgod.reflect.util.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解扫描器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class AnnotationsScanner extends Scanner<Annotation[]> {
    /** 单例对象 */
    private static final AnnotationsScanner INSTANCE = new AnnotationsScanner();

    /**
     * 隐藏无参构造方法
     */
    private AnnotationsScanner() {
    }

    /**
     * 获取单例对象
     * @return 单例对象
     */
    public static AnnotationsScanner getInstance() {
        return INSTANCE;
    }

    /**
     * 扫描注解，返回注解对应的决策器
     * @param annotations 注解，可包含非决策注解，会自动剔除
     * @return 决策器
     */
    @Override
    public DecidableMeta scan(Annotation[] annotations) {
        List<Decider<?>> deciders = new ArrayList<>(annotations.length);
        for(Annotation annotation : annotations) {
            Decider<?> decider = scan(annotation);
            if(decider != null) {
                deciders.add(decider);
            }
        }
        return new DecidableMeta() {
            @Override
            public List<Decider<?>> getDeciders() {
                return deciders;
            }
        };
    }

    /**
     * 扫描单个注解，获取决策器
     * @param annotation 注解，可以不是决策注解
     * @return 决策器，不是决策注解返回null
     */
    private Decider<?> scan(Annotation annotation) {
        Class<? extends Annotation> annotationClass = annotation.annotationType();
        // 构造decider
        Decide decideAnnotation = annotationClass.getAnnotation(Decide.class);
        if(decideAnnotation == null) {
            return null;
        }
        Class<? extends Decider> deciderClass = decideAnnotation.decideBy();
        Decider<?> decider = ClassUtils.newInstance(deciderClass);
        // 设置decider属性
        Method[] annotationMethods = annotationClass.getDeclaredMethods();
        for(Method annotationMethod : annotationMethods) {
            String propertyName = null;
            // 从Property注解取属性名
            Property methodProperty = annotationMethod.getAnnotation(Property.class);
            if(methodProperty != null) {
                propertyName = methodProperty.value();
            }
            // 使用方法名
            if(propertyName == null) {
                propertyName = annotationMethod.getName();
            }
            // 查找setter
            String setterName = MethodUtils.getSetterNameByPropertyName(propertyName);
            List<Method> setters = ClassUtils.getMethods(deciderClass, setterName);
            if(setters.isEmpty()) {
                throw new ReflectException("找不到setter");
            }
            if(setters.size() > 1) {
                throw new ReflectException("找到多个setter");
            }
            Method setter = setters.get(0);
            // 获取注解值
            Object propertyValue = MethodUtils.invoke(annotationMethod, annotation);
            // 设置注解值
            MethodUtils.invoke(setter, decider, propertyValue);
        }
        return decider;
    }
}
