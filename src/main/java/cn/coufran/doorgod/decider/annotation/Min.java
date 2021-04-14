package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.decider.ComparableDecider;

import java.lang.annotation.*;

/**
 * 最小决策注解
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = ComparableDecider.class)
public @interface Min {
    /**
     * 定义边界值
     * @return 边界值
     */
    @Property("min")
    int value();

    /**
     * 边界值是否合法
     * @return 边界值合法返回true，否则返回false
     */
    @Property("includeMin")
    boolean include() default true;

    /**
     * 自定义消息
     * @return 自定义消息
     */
    String message() default "";
}
