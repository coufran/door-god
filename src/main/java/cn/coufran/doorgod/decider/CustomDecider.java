package cn.coufran.doorgod.decider;

import cn.coufran.doorgod.decider.Decider;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomDecider extends Decider {
    default boolean decide(Object o) {
        return this.decide();
    }

    boolean decide();
}
