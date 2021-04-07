package cn.coufran.doorgod.reflect;

/**
 * 反射相关异常，可能包装Java自带的反射异常，也可能是自行抛出的
 * @author Coufran
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
