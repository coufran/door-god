package cn.coufran.doorgod.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 属性扫描器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class FieldScanner extends Scanner<Field> {
    /** 单例对象 */
    private static final FieldScanner INSTANCE = new FieldScanner();

    /** 注解扫描器 */
    private AnnotationsScanner annotationsScanner = AnnotationsScanner.getInstance();

    /**
     * 隐藏构造方法
     */
    private FieldScanner() {
    }

    /**
     * 获取单例对象
     * @return 单例对象
     */
    public static FieldScanner getInstance() {
        return INSTANCE;
    }

    /**
     * 扫描属性，获取决策器
     * @param field 带扫描的属性
     * @return 决策器
     */
    @Override
    public DecidableMeta scan(Field field) {
        Annotation[] annotations = field.getAnnotations();
        DecidableMeta decidableMeta = annotationsScanner.scan(annotations);
        return decidableMeta;
    }
}
