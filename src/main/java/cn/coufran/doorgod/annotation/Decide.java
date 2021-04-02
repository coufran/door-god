package cn.coufran.doorgod.annotation;

import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.*;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Decide {
    Class<? extends Decider> decideBy();
}
