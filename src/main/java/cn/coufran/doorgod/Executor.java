package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.Message;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Executor {
    <V> void execute(V value, Decider<V> decider, Message message);
}
