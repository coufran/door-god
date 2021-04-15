package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.decider.EqualDecider;
import cn.coufran.doorgod.group.Groups;

import java.lang.annotation.*;

/**
 * 判等决策注解
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = EqualDecider.class)
public @interface Is {
    /**
     * 定义比较值
     * @return 比较值
     */
    @Property("compareValue")
    int value();

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
