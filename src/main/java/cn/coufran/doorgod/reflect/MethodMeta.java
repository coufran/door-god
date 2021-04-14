package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.message.GetterMethodAndValueTemplateMessage;
import cn.coufran.doorgod.message.Message;
import cn.coufran.doorgod.message.MessageTemplate;
import cn.coufran.doorgod.reflect.util.MethodUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法元数据
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodMeta extends DecidableMeta {
    /** 方法 */
    private Method method;
    /** 决策注解元数据 */
    private List<DecideAnnotationMeta> decideAnnotationMetas = new ArrayList<>();

    /**
     * 构造方法元数据
     * @param method 所属方法
     */
    public MethodMeta(Method method) {
        this.method = method;
    }

    /**
     * 获取所属方法
     * @return 所属方法
     */
    public Method getMethod() {
        return method;
    }

    public List<DecideAnnotationMeta> getDecideAnnotationMetas() {
        return decideAnnotationMetas;
    }

    /**
     * 执行getter方法，获取属性值
     */
    @Override
    public Object getValue(Object entity) {
        return MethodUtils.invoke(this.getMethod(), entity);
    }

    @Override
    public Message getMessage(MessageTemplate messageTemplate, Object value) {
        return new GetterMethodAndValueTemplateMessage(messageTemplate)
                .setGetterMethod(this.getMethod())
                .setValue(value);
    }

    /**
     * 添加决策注解元数据
     * @param decideAnnotationMetas 决策注解元数据
     */
    protected void addDecideAnnotationMetas(List<DecideAnnotationMeta> decideAnnotationMetas) {
        this.decideAnnotationMetas.addAll(decideAnnotationMetas);
    }

}
