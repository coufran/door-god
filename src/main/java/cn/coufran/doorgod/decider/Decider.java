package cn.coufran.doorgod.decider;

/**
 * 决策器父接口
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Decider<T> {
    /**
     * 决策，判定指定值是否符合决策规则
     * @param value 指定值
     * @return 合法返回true，否则返回false
     */
    boolean decide(T value);
}
