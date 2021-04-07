package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.CustomDecider;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.*;
import cn.coufran.doorgod.reflect.ClassMeta;
import cn.coufran.doorgod.reflect.ClassScanner;
import cn.coufran.doorgod.reflect.MethodMeta;
import cn.coufran.doorgod.reflect.util.MethodUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 校验器入口
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class Checker {
    /** 默认校验消息 */
    private static final String MESSAGE_DEFAULT = "未通过校验";

    /** 类扫描器 */
    private static ClassScanner classScanner = ClassScanner.getInstance();
    /** 校验执行器 */
    private static Executor executor = SimpleExecutor.getInstance();

    /**
     * 自定义决策规则的校验
     * @param decider 自定义决策器
     */
    public static void check(CustomDecider decider) {
        check(decider, MESSAGE_DEFAULT);
    }

    /**
     * 自定义决策规则的校验
     * @param decider 自定义决策器
     * @param message 非法消息
     */
    public static void check(CustomDecider decider, String message) {
        check(null, decider, message);
    }

    /**
     * 校验某个值
     * @param value 值
     * @param decider 决策器
     * @param <T> 值类型
     */
    public static <T> void check(T value, Decider<T> decider) {
        check(value, decider, MESSAGE_DEFAULT);
    }

    /**
     * 校验某个值
     * @param value 值
     * @param decider 决策器
     * @param message 非法消息
     * @param <T> 值类型
     */
    public static <T> void check(T value, Decider<T> decider, String message) {
        executor.execute(value, decider, new StringMessage(message));
    }

    /**
     * 校验POJO的某个属性
     * @param entity POJO
     * @param getMethod getter方法
     * @param decider 决策器
     * @param <T> POJO类型
     * @param <R> getter方法返回值类型
     */
    public static <T, R> void check(T entity, SerializableFunction<T, R> getMethod, Decider<R> decider) {
        R value = getMethod.apply(entity);

        MessageTemplate messageTemplate = MessageTemplateFactory.createMessageTemplate(decider);
        Message message = new GetterFunctionAndValueTemplateMessage(messageTemplate)
                .setGetterFunction(getMethod)
                .setValue(value);

        executor.execute(value, decider, message);
    }

    /**
     * 校验POJO
     * @param entity POJO对象
     * @param <T> POJO类型
     */
    public static <T> void check(T entity) {
        // 扫描类结构
        ClassMeta classMeta = classScanner.scan(entity.getClass());

        // 执行类校验
        List<Decider<?>> classDeciders = classMeta.getDeciders();
        String className = classMeta.getClazz().getSimpleName();
        for (Decider classDecider : classDeciders) {
            MessageTemplate messageTemplate = MessageTemplateFactory.createMessageTemplate(classDecider);
            Message message = new FieldNameAndValueTemplateMessage(messageTemplate)
                    .setFieldName(className)
                    .setValue(entity);
            executor.execute(entity, classDecider, message);
        }

        // 执行方法校验
        List<MethodMeta> methodMetas = classMeta.getMethodMetas();
        for(MethodMeta methodMeta : methodMetas) {
            Method method = methodMeta.getMethod();
            Object value = MethodUtils.invoke(method, entity);
            List<Decider<?>> methodDeciders = methodMeta.getDeciders();
            for(Decider methodDecider : methodDeciders) {
                MessageTemplate messageTemplate = MessageTemplateFactory.createMessageTemplate(methodDecider);
                Message message = new GetterMethodAndValueTemplateMessage(messageTemplate)
                        .setGetterMethod(method)
                        .setValue(value);
                executor.execute(value, methodDecider, message);
            }
        }
    }
}
