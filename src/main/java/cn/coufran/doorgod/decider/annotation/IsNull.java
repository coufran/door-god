package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.decider.IsNullDecider;

import java.lang.annotation.*;

/**
 * 非空决策注解
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Decide(decideBy = IsNullDecider.class)
public @interface IsNull {
}