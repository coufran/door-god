package cn.coufran.doorgod.reflect;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ReflectException extends RuntimeException {

    public ReflectException(Exception e) {
        super(e);
    }

    public ReflectException(String message) {
        super(message);
    }
}
