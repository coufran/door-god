package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.decider.NotNullDecider;

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
@Decide(decideBy = NotNullDecider.class)
public @interface NotNull {
    /**
     * 自定义消息
     * @return 自定义消息
     */
    String message() default "";
}
