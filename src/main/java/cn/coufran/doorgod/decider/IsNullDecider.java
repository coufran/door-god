package cn.coufran.doorgod.decider;

/**
 * 空决策器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class IsNullDecider implements Decider<Object> {
    @Override
    public boolean decide(Object value) {
        return value == null;
    }
}
