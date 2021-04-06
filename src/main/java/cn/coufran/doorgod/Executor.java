package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.Message;

/**
 * 校验执行器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Executor {
    /**
     * 执行校验
     * @param value 待校验的值
     * @param decider 决策器
     * @param message 校验不通过时的错误消息
     */
    <V> void execute(V value, Decider<V> decider, Message message);
}
