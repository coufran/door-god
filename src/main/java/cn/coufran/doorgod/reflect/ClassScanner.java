package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 类扫描器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassScanner extends Scanner<Class<?>> {
    /** 单例对象 */
    private static final ClassScanner INSTANCE = new ClassScanner();

    /** 方法扫描器 */
    private MethodScanner methodScanner = MethodScanner.getInstance();
    /** 注解扫描器 */
    private AnnotationsScanner annotationsScanner = AnnotationsScanner.getInstance();

    /**
     * 隐藏构造方法
     */
    private ClassScanner() {
    }

    /**
     * 获取单例对象
     * @return 单例对象
     */
    public static ClassScanner getInstance() {
        return INSTANCE;
    }

    /**
     * 扫描类结构，类可被决策注解标记，会递归扫描方法结构
     * @param clazz 待扫描的类
     * @return 类结构元数据
     */
    public ClassMeta scan(Class<?> clazz) {
        // 构造元数据
        ClassMeta classMeta = new ClassMeta(clazz);
        // 解析Annotation
        List<DecideAnnotationMeta> decideAnnotationMetas
                = annotationsScanner.scan(clazz.getAnnotations()).getDecideAnnotationMetas();
        classMeta.addDecideAnnotationMetas(decideAnnotationMetas);
        // 解析方法
        List<MethodMeta> methodMetas = Arrays.stream(clazz.getMethods())
                .map(methodScanner::scan)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        classMeta.setMethodMetas(methodMetas);

        return classMeta;
    }
}
