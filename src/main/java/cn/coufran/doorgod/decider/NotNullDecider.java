package cn.coufran.doorgod.decider;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class NotNullDecider implements Decider {
    @Override
    public boolean decide(Object value) {
        return value != null;
    }
}
