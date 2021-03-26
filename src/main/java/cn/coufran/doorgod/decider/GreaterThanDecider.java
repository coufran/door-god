package cn.coufran.doorgod.decider;

import cn.coufran.doorgod.Decider;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class GreaterThanDecider<T extends Comparable<T>> implements Decider<T> {

    private T compareValue;

    public GreaterThanDecider(T compareValue) {
        this.compareValue = compareValue;
    }

    @Override
    public boolean decide(T value) {
        return compareValue.compareTo(value) < 0;
    }
}
