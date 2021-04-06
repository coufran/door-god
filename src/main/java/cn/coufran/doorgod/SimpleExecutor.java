package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.Message;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class SimpleExecutor implements Executor {
    private static final SimpleExecutor INSTANCE = new SimpleExecutor();

    public static SimpleExecutor getInstance() {
        return INSTANCE;
    }

    private SimpleExecutor() {
    }

    @Override
    public <V> void execute(V value, Decider<V> decider, Message message) {
        if(!decider.decide(value)) {
            String messageString = message.asString();
            throw new ValidateException(messageString);
        }
    }
}
