package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

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
    /** 类级别的决策器 */
    private List<Decider<?>> deciders = new ArrayList<>();
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

    /**
     * 获取类级别的决策器
     */
    @Override
    public List<Decider<?>> getDeciders() {
        return this.deciders;
    }

    /**
     * 添加类级别的决策器
     * @param deciderClasses 决策器
     */
    protected void addDeciders(List<Decider<?>> deciderClasses) {
        this.deciders.addAll(deciderClasses);
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
