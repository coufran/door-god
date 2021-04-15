package cn.coufran.doorgod.message;

/**
 * 字符串消息。<br>
 * 该错误消息是静态消息，不随任何内容变化。
 * <p>
 * 用法如下：
 * <pre>
 * Message message = new StringMessage("error message");
 * </pre>
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringMessage extends Message {
    /** 消息字符串 */
    private String message;

    /**
     * 构造字符串消息
     * @param message 消息字符串
     */
    public StringMessage(String message) {
        this.message = message;
    }

    /**
     * 返回消息字符串
     */
    @Override
    public String asString() {
        return message;
    }
}
