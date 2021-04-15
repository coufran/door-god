package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.Message;
import cn.coufran.doorgod.message.MessageTemplate;
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
        List<DecideAnnotationMeta> decideAnnotationMetas = new ArrayList<>(annotations.length);
        for(Annotation annotation : annotations) {
            DecideAnnotationMeta decideAnnotationMeta = scan(annotation);
            if(decideAnnotationMeta != null) {
                decideAnnotationMetas.add(decideAnnotationMeta);
            }
        }
        return new DecidableMeta() {
            @Override
            public List<DecideAnnotationMeta> getDecideAnnotationMetas() {
                return decideAnnotationMetas;
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
     * 扫描单个注解，获取决策器
     * @param annotation 注解，可以不是决策注解
     * @return 决策器，不是决策注解返回null
     */
    private DecideAnnotationMeta scan(Annotation annotation) {
        Class<? extends Annotation> annotationClass = annotation.annotationType();
        // 获取Decide标记注解
        Decide decideAnnotation = annotationClass.getAnnotation(Decide.class);
        if(decideAnnotation == null) { // 不是Decide注解，返回null
            return null;
        }

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
                decideAnnotationMeta.setMessage(message);
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
