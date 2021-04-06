package cn.coufran.doorgod.message;

/**
 * 错误消息
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Message {
    /**
     * 将错误消息序列化成字符串
     * @return 错误消息字符串
     */
    public abstract String asString();
}
