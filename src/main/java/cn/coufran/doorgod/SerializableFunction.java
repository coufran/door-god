package cn.coufran.doorgod;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 方法指针
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@FunctionalInterface
public interface SerializableFunction<T, R>
        extends Function<T, R>, Serializable {

}
