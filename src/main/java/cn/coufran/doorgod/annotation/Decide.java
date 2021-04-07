package cn.coufran.doorgod.annotation;

import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.*;

/**
 * 定义决策注解，指定决策注解使用的决策器<br/>
 * 决策器必须拥有无参构造方法。
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Decide {
    /**
     * 指定决策器
     * @return 决策器类
     */
    Class<? extends Decider> decideBy();
}
