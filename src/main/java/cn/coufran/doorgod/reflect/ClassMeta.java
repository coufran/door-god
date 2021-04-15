package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.Executor;
import cn.coufran.doorgod.message.FieldNameAndValueTemplateMessage;
import cn.coufran.doorgod.message.Message;
import cn.coufran.doorgod.message.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 类结构元数据
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassMeta extends DecidableMeta {
    /** 类 */
    private Class<?> clazz;
    /** 决策注解元数据 */
    private List<DecideAnnotationMeta> decideAnnotationMetas = new ArrayList<>();
    /** 方法元数据 */
    private List<MethodMeta> methodMetas;

    /**
     * 构造类结构元数据
     * @param clazz 指定所属类
     */
    protected ClassMeta(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * 获取所属类
     * @return 所属类
     */
    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public List<DecideAnnotationMeta> getDecideAnnotationMetas() {
        return this.decideAnnotationMetas;
    }

    /**
     * 决策值就是实体本身
     */
    @Override
    public Object getValue(Object entity) {
        return entity;
    }

    @Override
    public Message getMessage(MessageTemplate messageTemplate, Object value) {
        return new FieldNameAndValueTemplateMessage(messageTemplate)
                .setFieldName(this.getClazz().getSimpleName())
                .setValue(value);
    }

    @Override
    public void accept(Executor executor, Object entity, String group) {
        super.accept(executor, entity, group);
        for (MethodMeta methodMeta : this.methodMetas) {
            methodMeta.accept(executor, entity, group);
        }
    }

    /**
     * 添加决策注解元数据
     * @param decideAnnotationMetas 决策注解元数据
     */
    protected void addDecideAnnotationMetas(List<DecideAnnotationMeta> decideAnnotationMetas) {
        this.decideAnnotationMetas.addAll(decideAnnotationMetas);
    }

    /**
     * 获取方法元数据
     * @return 方法元数据
     */
    public List<MethodMeta> getMethodMetas() {
        return methodMetas;
    }

    /**
     * 设置方法元数据
     * @param methodMetas 方法元数据
     */
    protected void setMethodMetas(List<MethodMeta> methodMetas) {
        this.methodMetas = methodMetas;
    }
}
