package cn.coufran.doorgod.decider;

import cn.coufran.doorgod.Decider;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class IsNullDecider implements Decider {

    @Override
    public boolean decide(Object value) {
        return value == null;
    }
}
