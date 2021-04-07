package cn.coufran.doorgod;

/**
 * 异常消息校验器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class ExceptionTests {

    /**
     * 运行异常代码块
     * @param runnable 异常代码块
     * @return 抛出的异常的消息
     */
    protected String run(Runnable runnable) {
        try {
            runnable.run();
        } catch (ValidateException e) {
            return e.getMessage();
        }
        return null;
    }
}
