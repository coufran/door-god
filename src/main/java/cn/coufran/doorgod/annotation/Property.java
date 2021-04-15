package cn.coufran.doorgod.annotation;

import java.lang.annotation.*;

/**
 * 指定决策注解属性对应的决策器属性<br>
 * 决策器必须有对应属性名的setter方法
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Property {
    /**
     * 定义决策器属性名
     * @return 决策器属性名
     */
    String value() default "";
}
