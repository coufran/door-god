package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.decider.ComparableDecider;

import java.lang.annotation.*;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = ComparableDecider.class)
public @interface Min {
    @Property("min")
    int value();
    @Property("includeMin")
    boolean include() default true;
}
