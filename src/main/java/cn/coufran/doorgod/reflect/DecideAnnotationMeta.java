package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.util.HashMap;
import java.util.Map;

/**
 * 决策注解元数据
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class DecideAnnotationMeta {
    /** 决策器类 */
    private Class<? extends Decider> deciderClass;
    /** 决策器参数 */
    private Map<String, Object> properties = new HashMap<>();
    /** 决策消息 */
    private String message;

    /**
     * 获取决策器类
     * @return 决策器类
     */
    Class<? extends Decider> getDeciderClass() {
        return deciderClass;
    }

    /**
     * 设置决策器类
     * @param deciderClass 决策器类
     */
    void setDeciderClass(Class<? extends Decider> deciderClass) {
        this.deciderClass = deciderClass;
    }

    /**
     * 获取决策参数
     * @return 决策参数
     */
    public Map<String, Object> getProperties() {
        return properties;
    }

    /**
     * 添加决策器参数
     * @param name 决策器参数名
     * @param value 决策器参数值
     */
    void addParameter(String name, Object value) {
        this.properties.put(name, value);
    }

    /**
     * 获取决策消息
     * @return 决策消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置决策消息
     * @param message 决策消息
     */
    void setMessage(String message) {
        this.message = message;
    }
}
