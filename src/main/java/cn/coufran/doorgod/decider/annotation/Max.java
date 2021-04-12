package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.decider.ComparableDecider;

import java.lang.annotation.*;

/**
 * 最大决策注解
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = ComparableDecider.class)
public @interface Max {
    /**
     * 定义边界值
     * @return 边界值
     */
    @Property("max")
    int value();

    /**
     * 边界值是否合法
     * @return 边界值合法返回true，否则返回false
     */
    @Property("includeMax")
    boolean include() default true;
}
