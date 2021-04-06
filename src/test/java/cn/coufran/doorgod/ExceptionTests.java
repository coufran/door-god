package cn.coufran.doorgod;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExceptionTests {

    protected String run(Runnable runnable) {
        try {
            runnable.run();
        } catch (ValidateException e) {
            return e.getMessage();
        }
        return null;
    }
}
