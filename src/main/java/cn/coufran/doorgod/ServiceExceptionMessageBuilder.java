package cn.coufran.doorgod;

import java.util.function.Function;

/**
 *
 * @author liuhm8
 * @since 1.0.0
 * @version 1.0.0
 */
public class ServiceExceptionMessageBuilder {
    public static String isNull(String fieldName) {
        return fieldName + "is null";
    }

//    public static <T, R> String isNull(Function<T, R> function) {
//
//        return isNull(fieldName);
//    }
}
