package cn.coufran.doorgod.decider;

/**
 * 非空决策器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class NotNullDecider implements Decider<Object> {
    /**
     * 判定指定值是否非空
     */
    @Override
    public boolean decide(Object value) {
        return value != null;
    }
}
