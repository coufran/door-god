package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.Message;

/**
 * 简单校验执行器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class SimpleExecutor implements Executor {
    /** 单例对象 */
    private static final SimpleExecutor INSTANCE = new SimpleExecutor();

    /**
     * 获取执行器实例
     * @return 执行器
     */
    public static SimpleExecutor getInstance() {
        return INSTANCE;
    }

    /**
     * 默认构造方法
     */
    private SimpleExecutor() {
    }

    /**
     * 调用决策器的决策方法进行校验，校验未通过时，将错误消息封装为{@link ValidateException}抛出
     * @throws ValidateException 校验未通过
     */
    @Override
    public <V> void execute(V value, Decider<V> decider, Message message) {
        if(!decider.decide(value)) {
            String messageString = message.asString();
            throw new ValidateException(messageString);
        }
    }
}
