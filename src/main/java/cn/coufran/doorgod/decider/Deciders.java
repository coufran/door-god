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
     * @return 非空决策器
     */
    public static NotNullDecider notNull() {
        return new NotNullDecider();
    }
}
