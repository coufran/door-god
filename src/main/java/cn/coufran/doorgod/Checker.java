package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.CustomDecider;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.group.Groups;
import cn.coufran.doorgod.message.*;
import cn.coufran.doorgod.reflect.ClassMeta;
import cn.coufran.doorgod.reflect.ClassScanner;
import cn.coufran.doorgod.reflect.DecideAnnotationMeta;
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
     * @param <V> 值类型
     */
    public static <V> void check(V value, Decider<V> decider) {
        check(value, decider, MESSAGE_DEFAULT);
    }

    /**
     * 校验某个值
     * @param value 值
     * @param decider 决策器
     * @param message 非法消息
     * @param <V> 值类型
     */
    public static <V> void check(V value, Decider<V> decider, String message) {
        executor.execute(value, decider, new StringMessage(message));
    }

    /**
     * 校验POJO的某个属性
     * @param entity POJO
     * @param getMethod getter方法
     * @param decider 决策器
     * @param <E> POJO类型
     * @param <V> getter方法返回值类型
     */
    public static <E, V> void check(E entity, SerializableFunction<E, V> getMethod, Decider<V> decider) {
        V value = getMethod.apply(entity);

        MessageTemplate messageTemplate = MessageTemplateFactory.createMessageTemplate(decider);
        Message message = new GetterFunctionAndValueTemplateMessage(messageTemplate)
                .setGetterFunction(getMethod)
                .setValue(value);

        executor.execute(value, decider, message);
    }

    /**
     * 校验POJO
     * @param entity POJO对象
     * @param <E> POJO类型
     */
    public static <E> void check(E entity) {
        Checker.check(entity, Groups.DEFAULT);
    }

    /**
     * 校验POJO
     * @param entity POJO对象
     * @param group 校验组
     * @param <E> POJO类型
     */
    public static <E> void check(E entity, String group) {
        // 扫描类结构
        ClassMeta classMeta = classScanner.scan(entity.getClass());
        classMeta.accept(executor, entity, group);
    }
}
