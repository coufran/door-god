package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Scanner<T> {
    Decidable scan(T t);

    default List<Decider> parseDecider(Annotation[] annotations) {
        return AnnotationsScanner.getInstance()
                .scan(annotations)
                .getDeciders();
    }
}
