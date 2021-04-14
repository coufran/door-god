package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.reflect.util.ClassUtils;
import cn.coufran.doorgod.reflect.util.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法扫描器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodScanner extends Scanner<Method> {
    /** 单例对象 */
    private static final MethodScanner INSTANCE = new MethodScanner();

    /** 属性扫描器 */
    private FieldScanner fieldScanner = FieldScanner.getInstance();
    /** 注解扫描器 */
    private AnnotationsScanner annotationsScanner = AnnotationsScanner.getInstance();

    /**
     * 隐藏构造方法
     */
    private MethodScanner() {
    }

    /**
     * 获取单例对象
     * @return 获取单例对象
     */
    public static MethodScanner getInstance() {
        return INSTANCE;
    }

    /**
     * 扫描方法，如果方法有对应的属性，同时扫描属性
     * @param method 待扫描的方法
     * @return 方法元数据，如果方法不是getter，返回null
     */
    @Override
    public MethodMeta scan(Method method) {
        if(!MethodUtils.isGetter(method)) {
            return null;
        }
        // 构造元数据
        MethodMeta methodMeta = new MethodMeta(method);
        // 扫描Annotation
        Annotation[] annotations = method.getAnnotations();
        List<DecideAnnotationMeta> decideAnnotationMetas
                = annotationsScanner.scan(annotations).getDecideAnnotationMetas();
        methodMeta.addDecideAnnotationMetas(decideAnnotationMetas);
        // 扫描字段
        String fieldName = MethodUtils.getFieldNameByGetter(method);
        Field field = ClassUtils.getField(method.getDeclaringClass(), fieldName);
        if(field != null) {
            DecidableMeta fieldDecidable = fieldScanner.scan(field);
            methodMeta.addDecideAnnotationMetas(fieldDecidable.getDecideAnnotationMetas());
        }

        return methodMeta;
    }
}
