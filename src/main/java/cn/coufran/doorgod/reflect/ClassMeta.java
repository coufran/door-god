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
    private List<Class<? extends Decider>> deciderClasses = new ArrayList<>();
    private List<MethodMeta> methodMetas;

    public ClassMeta(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<Class<? extends Decider>> getDeciderClasses() {
        return this.deciderClasses;
    }

    public void addDeciderClasses(List<Class<? extends Decider>> deciderClasses) {
        this.deciderClasses.addAll(deciderClasses);
    }

    public List<MethodMeta> getMethodMetas() {
        return methodMetas;
    }

    public void setMethodMetas(List<MethodMeta> methodMetas) {
        this.methodMetas = methodMetas;
    }
}
