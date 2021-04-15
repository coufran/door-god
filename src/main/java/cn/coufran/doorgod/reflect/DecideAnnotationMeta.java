package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.util.Arrays;
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
    /** 决策消息模版 */
    private String messageTemplate;
    /** 决策组 */
    private String[] groups;

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
     * 获取决策消息模版
     * @return 决策消息模版
     */
    public String getMessageTemplate() {
        return messageTemplate;
    }

    /**
     * 设置决策消息模版
     * @param messageTemplate 决策消息模版
     */
    void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    /**
     * 是否包含校验组
     * @param group 校验组
     * @return 是否包含校验组
     */
    public boolean containGroup(String group) {
        if(groups == null) {
            return false;
        }
        return Arrays.asList(groups).contains(group);
    }

    /**
     * 设置校验组
     * @param groups 校验组
     */
    void setGroups(String[] groups) {
        this.groups = groups;
    }
}
