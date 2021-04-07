package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

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
    /** 决策器 */
    private List<Decider<?>> deciders = new ArrayList<>();

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

    /**
     * 获取决策器
     * @return 决策器
     */
    public List<Decider<?>> getDeciders() {
        return deciders;
    }

    /**
     * 添加决策器
     * @param deciders 决策器
     */
    protected void addDeciders(List<Decider<?>> deciders) {
        this.deciders.addAll(deciders);
    }

}
