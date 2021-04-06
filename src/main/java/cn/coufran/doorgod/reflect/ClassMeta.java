package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassMeta implements Decidable {
    private Class clazz;
    private List<Decider> deciders = new ArrayList<>();
    private List<MethodMeta> methodMetas;

    public ClassMeta(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<Decider> getDeciders() {
        return this.deciders;
    }

    public void addDeciders(List<Decider> deciderClasses) {
        this.deciders.addAll(deciderClasses);
    }

    public List<MethodMeta> getMethodMetas() {
        return methodMetas;
    }

    public void setMethodMetas(List<MethodMeta> methodMetas) {
        this.methodMetas = methodMetas;
    }
}
