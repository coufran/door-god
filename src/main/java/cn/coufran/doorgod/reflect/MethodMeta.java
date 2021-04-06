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
public class MethodMeta implements Decidable {
    private Method method;
    private List<Decider> deciders = new ArrayList<>();

    public MethodMeta(Method method) {
        this.method = method;
    }

    String getFieldName() {
        String fieldName = method.getName();
        if(fieldName.startsWith("get")) {
            fieldName = fieldName.replaceFirst("get", "");
        } else if(fieldName.startsWith("is")) {
            fieldName = fieldName.replaceFirst("is", "");
        } else {
            return null;
        }
        char[] fieldNameChars = fieldName.toCharArray();
        if(fieldNameChars[0] >= 'A' && fieldNameChars[0] <= 'Z') {
            fieldNameChars[0] = (char) (fieldNameChars[0] - ('A' - 'a'));
            fieldName = new String(fieldNameChars);
        }
        return fieldName;
    }

    public Method getMethod() {
        return method;
    }

    public List<Decider> getDeciders() {
        return deciders;
    }

    public void addDeciders(List<Decider> deciders) {
        this.deciders.addAll(deciders);
    }

}
