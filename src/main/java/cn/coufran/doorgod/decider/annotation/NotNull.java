package cn.coufran.doorgod.decider.annotation;

import cn.coufran.doorgod.annotation.Decide;
import cn.coufran.doorgod.annotation.DecideList;
import cn.coufran.doorgod.decider.NotNullDecider;
import cn.coufran.doorgod.group.Groups;

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

    /**
     * 校验组
     * @return 校验组
     */
    String[] groups() default Groups.DEFAULT;

    /**
     * 决策注解集
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
    @DecideList
    @interface List {
        /**
         * 相同类型的决策注解
         * @return 相同类型的决策注解
         */
        NotNull[] value();
    }

}
