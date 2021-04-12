package cn.coufran.doorgod.decider;

/**
 * 决策器工厂
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class Deciders {
    /**
     * 非空
     * @return 决策器
     */
    public static Decider notNull() {
        return new NotNullDecider();
    }

    /**
     * 是空
     * @return 决策器
     */
    public static Decider isNull() {
        return new IsNullDecider();
    }


    /**
     * 大于
     * @param value 边界值
     * @return 决策器
     */
    public static <V extends Comparable<V>> Decider<V> greaterThan(V value) {
        return new ComparableDecider<V>(value, false, null, true);
    }

    /**
     * 大于等于
     * @param value 边界值
     * @return 决策器
     */
    public static <V extends Comparable<V>> Decider<V> greaterEqual(V value) {
        return new ComparableDecider<V>(value, true, null, true);
    }

    /**
     * 小于
     * @param value 边界值
     * @return 决策器
     */
    public static <V extends Comparable<V>> Decider<V> lessThan(V value) {
        return new ComparableDecider<V>(null, true, value, false);
    }

    /**
     * 小于等于
     * @param value 边界值
     * @return 决策器
     */
    public static <V extends Comparable<V>> Decider<V> lessEqual(V value) {
        return new ComparableDecider<V>(null, true, value, true);
    }

    /**
     * 范围内
     * @param min 最小值
     * @param max 最大值
     * @return 决策器
     */
    public static <V extends Comparable<V>> Decider<V> between(V min, V max) {
        return new ComparableDecider<V>(min, max);
    }

    /**
     * 范围内
     * @param min 最小值
     * @param includeMin 是否包含最小值
     * @param max 最大值
     * @param includeMax 是否包含最大值
     * @return 决策器
     */
    public static <V extends Comparable<V>> Decider<V> between(V min, boolean includeMin, V max, boolean includeMax) {
        return new ComparableDecider<V>(min, includeMin, max, includeMax);
    }

    /**
     * 相同
     * @param value 比较值
     * @return 决策器
     */
    public static <V> Decider is(V value) {
        return new EqualDecider(value);
    }

    /**
     * 不同
     * @param value 比较值
     * @return 决策器
     */
    public static <V> Decider not(V value) {
        return new NotEqualDecider(value);
    }
}
