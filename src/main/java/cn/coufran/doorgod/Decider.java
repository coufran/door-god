package cn.coufran.doorgod;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Decider<T> {
    boolean decide(T t);
}
