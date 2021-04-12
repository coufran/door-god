package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.Property;
import cn.coufran.doorgod.decider.NotEqualDecider;

import java.lang.annotation.*;

/**
 * 不等决策注解
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = NotEqualDecider.class)
public @interface Not {
    /**
     * 定义比较值
     * @return 比较值
     */
    @Property("compareValue")
    int value();
}