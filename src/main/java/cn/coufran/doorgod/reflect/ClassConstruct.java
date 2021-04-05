package cn.coufran.doorgod.reflect;

import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class ClassConstruct {
    private Class classMeta;
    private List<MethodConstruct> methodConstructs;

    public ClassConstruct(Class classMeta) {
        this.classMeta = classMeta;
    }

    public void setMethodConstructs(List<MethodConstruct> methodConstructs) {
        this.methodConstructs = methodConstructs;
    }

    public List<MethodConstruct> getMethodConstructs() {
        return methodConstructs;
    }
}
