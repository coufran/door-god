package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.CustomDecider;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.reflect.ClassConstruct;
import cn.coufran.doorgod.reflect.ClassScanner;
import cn.coufran.doorgod.reflect.MethodConstruct;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class Checker {
    private static final String MESSAGE_DEFAULT = "未通过校验";

    private static ClassScanner classScanner = new ClassScanner();
    private static CheckExecutor checkExecutor = new CheckExecutor();

    public static <T> void check(CustomDecider decider) {
        check(decider, MESSAGE_DEFAULT);
    }

    public static <T> void check(CustomDecider decider, String message) {
        check(null, decider, message);
    }

    public static <T> void check(T value, Decider decider) {
        check(value, decider, MESSAGE_DEFAULT);
    }

    public static <T> void check(T value, Decider decider, String message) {
        if(!decider.decide(value)) {
            throw new ValidateException(message);
        }
    }

    public static <T, R> void check(T entity, SerializableFunction<T, R> getMethod, Decider decider) {
        R value = getMethod.apply(entity);
        String implMethodName = null;
        try {
            Method method = getMethod.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(getMethod);
            implMethodName = serializedLambda.getImplMethodName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(implMethodName.startsWith("is")) {
            implMethodName = implMethodName.replaceFirst("is", "");
        }
        if(implMethodName.startsWith("get")) {
            implMethodName = implMethodName.replaceFirst("get", "");
        }
        char[] cs = implMethodName.toCharArray();
        cs[0] = (char) (cs[0] - ('A' - 'a'));
        implMethodName = new String(cs);

        check(value, decider, implMethodName + "未通过校验");
    }

    public static <T> void check(T entity) {
        ClassConstruct classConstruct = classScanner.scan(entity.getClass());
        List<MethodConstruct> methodConstructs = classConstruct.getMethodConstructs();
        for(MethodConstruct methodConstruct : methodConstructs) {
            Method method = methodConstruct.getMethodMeta();
            Object value;
            try {
                value = method.invoke(entity);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
            List<Class<? extends Decider>> deciders = methodConstruct.getDeciders();
            for(Class<? extends Decider> decider : deciders) {
                checkExecutor.execute(value, decider);
            }
        }
    }
}
