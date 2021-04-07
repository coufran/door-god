package cn.coufran.doorgod.annotation;

import java.lang.annotation.*;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Property {
    String value();
}
