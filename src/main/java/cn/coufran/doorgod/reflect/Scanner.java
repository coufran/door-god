package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Scanner<T> {
    Decidable scan(T t);

    default List<Class<? extends Decider>> parseDecider(Annotation[] annotations) {
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
}
