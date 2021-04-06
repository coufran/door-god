package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassScanner implements Scanner<Class<?>> {
    private static final ClassScanner INSTANCE = new ClassScanner();

    private MethodScanner methodScanner = MethodScanner.getInstance();

    private ClassScanner() {
    }

    public static ClassScanner getInstance() {
        return INSTANCE;
    }

    public ClassMeta scan(Class<?> clazz) {
        // 构造元数据
        ClassMeta classMeta = new ClassMeta(clazz);
        // 解析Annotation
        List<Decider> deciderClasses = this.parseDecider(clazz.getAnnotations());
        classMeta.addDeciders(deciderClasses);
        // 解析方法
        List<MethodMeta> methodMetas = Arrays.stream(clazz.getMethods())
                .map(methodScanner::scan)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        classMeta.setMethodMetas(methodMetas);

        return classMeta;
    }
}
