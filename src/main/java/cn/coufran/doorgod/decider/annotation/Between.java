package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.decider.ComparableDecider;
import cn.coufran.doorgod.group.Groups;

import java.lang.annotation.*;

/**
 * 区间决策注解
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = ComparableDecider.class)
public @interface Between {
    /**
     * 定义最小边界值
     * @return 边界值
     */
    @Property
    int min();

    /**
     * 定义最大边界值
     * @return 边界值
     */
    @Property
    int max();

    /**
     * 最小值是否合法
     * @return 边界值合法返回true，否则返回false
     */
    @Property
    boolean includeMin() default true;

    /**
     * 最大值是否合法
     * @return 边界值合法返回true，否则返回false
     */
    @Property
    boolean includeMax() default true;

    /**
     * 自定义消息
     * @return 自定义消息
     */
    String message() default "";

    /**
     * 校验组
     * @return 校验组
     */
    String[] groups() default Groups.DEFAULT;
}
