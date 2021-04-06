package cn.coufran.doorgod.decider;

import cn.coufran.doorgod.decider.*;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class Deciders {
    public static Decider isNull() {
        return new IsNullDecider();
    }

    public static Decider notNull() {
        return new NotNullDecider();
    }

    public static Decider notBlank() {
        return new NotBlankDecider();
    }

    public static <T extends Comparable<T>> Decider<T> greaterThan(T compareValue) {
        return new GreaterThanDecider<>(compareValue);
    }

    public static <T extends Comparable<T>> Decider<T> lessEqual(T compareValue) {
        return new LessEqualDecider<>(compareValue);
    }
}
