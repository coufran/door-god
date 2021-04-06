package cn.coufran.doorgod.message;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringMessage extends Message {
    private String message;

    public StringMessage(String message) {
        this.message = message;
    }

    @Override
    public String asString() {
        return message;
    }
}
