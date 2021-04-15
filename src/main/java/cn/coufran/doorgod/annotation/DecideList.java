package cn.coufran.doorgod.annotation;

import java.lang.annotation.*;

/**
 * 标记判定注解集
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface DecideList {
}
