package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.DecideList;
import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.Message;
import cn.coufran.doorgod.message.MessageTemplate;
import cn.coufran.doorgod.reflect.util.ClassUtils;
import cn.coufran.doorgod.reflect.util.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
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
     * 扫描注解，返回注解对应的决策注解元数据
     * @param annotations 注解，可包含非决策注解，会自动剔除
     * @return 决策注解元数据
     */
    @Override
    public DecidableMeta scan(Annotation[] annotations) {
        List<DecideAnnotationMeta> allDecideAnnotationMetas = new ArrayList<>(annotations.length);
        for(Annotation annotation : annotations) {
            List<DecideAnnotationMeta> decideAnnotationMetas = scan(annotation);
            if(decideAnnotationMetas != null) {
                allDecideAnnotationMetas.addAll(decideAnnotationMetas);
            }
        }
        return new DecidableMeta() {
            @Override
            public List<DecideAnnotationMeta> getDecideAnnotationMetas() {
                return allDecideAnnotationMetas;
            }

            @Override
            public Object getValue(Object entity) {
                return null;
            }

            @Override
            public Message getMessage(MessageTemplate messageTemplate, Object value) {
                return null;
            }
        };
    }

    /**
     * 扫描单个注解，获取决策注解元数据
     * @param annotation 注解，可以不是决策注解
     * @return 决策注解元数据，不是决策注解返回空集合
     */
    private List<DecideAnnotationMeta> scan(Annotation annotation) {
        Class<? extends Annotation> annotationClass = annotation.annotationType();
        // 如果是DecideList注解
        DecideList decideListAnnotation = annotationClass.getAnnotation(DecideList.class);
        if(decideListAnnotation != null) {
            return this.scanDecideListAnnotation(annotation, annotationClass);
        }
        // 如果是Decide注解
        Decide decideAnnotation = annotationClass.getAnnotation(Decide.class);
        if(decideAnnotation != null) { // 不是Decide注解，返回null
            return Collections.singletonList(
                    scanDecideAnnotation(annotation, annotationClass, decideAnnotation)
            );
        }

        return Collections.emptyList();

    }

    /**
     * 扫描决策List注解，获取决策注解元数据
     * @param listAnnotation 决策List注解
     * @param annotationClass 注解类
     * @return 所有的决策注解元数据
     */
    private List<DecideAnnotationMeta> scanDecideListAnnotation(
            Annotation listAnnotation,
            Class<? extends Annotation> annotationClass) {
        // 获取所有标注的Decide注解
        Method valueMethod = ClassUtils.getMethod(annotationClass, "value");
        Annotation[] annotations = MethodUtils.invoke(valueMethod, listAnnotation);
        // 依次扫描
        List<DecideAnnotationMeta> allDecideAnnotationMetas = new ArrayList<>();
        for (Annotation annotation : annotations) {
            List<DecideAnnotationMeta> decideAnnotationMetas = this.scan(annotation);
            allDecideAnnotationMetas.addAll(decideAnnotationMetas);
        }
        return allDecideAnnotationMetas;
    }

    /**
     * 扫描决策注解，获取决策注解元数据
     * @param annotation 决策注解
     * @param annotationClass Decide注解类对象
     * @param decideAnnotation Decide注解
     * @return 策注解元数据
     */
    private DecideAnnotationMeta scanDecideAnnotation(
            Annotation annotation,
            Class<? extends Annotation> annotationClass,
            Decide decideAnnotation) {
        // 开始构造元数据
        DecideAnnotationMeta decideAnnotationMeta = new DecideAnnotationMeta();
        // 获取Decider Class
        Class<? extends Decider> deciderClass = decideAnnotation.decideBy();
        decideAnnotationMeta.setDeciderClass(deciderClass);
        // 获取属性
        Method[] annotationMethods = annotationClass.getDeclaredMethods();
        for(Method annotationMethod : annotationMethods) {
            Property methodProperty = annotationMethod.getAnnotation(Property.class);
            if(methodProperty == null) { // 非决策器参数，跳过
                continue;
            }
            // 获取属性名
            String propertyName = methodProperty.value();
            if("".equals(propertyName)) { // 未设值参数名，使用方法名
                propertyName = annotationMethod.getName();
            }
            // 获取属性值
            Object propertyValue = MethodUtils.invoke(annotationMethod, annotation);
            // 设置属性
            decideAnnotationMeta.addParameter(propertyName, propertyValue);
        }
        // 获取注解消息
        Method messageMethod = ClassUtils.getMethod(annotationClass, "message");
        if(messageMethod != null) {
            String message = MethodUtils.invoke(messageMethod, annotation);
            if(!"".equals(message)) {
                decideAnnotationMeta.setMessageTemplate(message);
            }
        }
        // 获取决策组
        Method groupsMethod = ClassUtils.getMethod(annotationClass, "groups");
        if(groupsMethod != null) {
            String[] groups = MethodUtils.invoke(groupsMethod, annotation);
            decideAnnotationMeta.setGroups(groups);
        }
        return decideAnnotationMeta;
    }
}
