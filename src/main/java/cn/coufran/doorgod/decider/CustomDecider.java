package cn.coufran.doorgod.decider;

/**
 * 匿名决策器模版
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CustomDecider extends Decider<Object> {
    /**
     * 重写决策方法，调用无参决策
     */
    default boolean decide(Object o) {
        return this.decide();
    }

    /**
     * 无参决策
     * @return 合法返回true，非法返回false
     */
    boolean decide();
}
