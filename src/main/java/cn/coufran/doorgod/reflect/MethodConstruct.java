package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class MethodConstruct {
    private Method methodMeta;
    private List<Class<? extends Decider>> deciders = new ArrayList<>();

    public MethodConstruct(Method methodMeta) {
        this.methodMeta = methodMeta;
    }

    public Method getMethodMeta() {
        return methodMeta;
    }

    public List<Class<? extends Decider>> getDeciders() {
        return deciders;
    }

    public void addDeciders(List<Class<? extends Decider>> deciders) {
        this.deciders.addAll(deciders);
    }

}
