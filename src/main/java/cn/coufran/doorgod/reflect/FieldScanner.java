package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class FieldScanner implements Scanner<Field> {
    private static final FieldScanner INSTANCE = new FieldScanner();

    private FieldScanner() {
    }

    public static FieldScanner getInstance() {
        return INSTANCE;
    }

    @Override
    public Decidable scan(Field field) {
        Annotation[] annotations = field.getAnnotations();
        List<Class<? extends Decider>> deciderClasses = this.parseDecider(annotations);

        return new Decidable() {
            @Override
            public List<Class<? extends Decider>> getDeciderClasses() {
                return deciderClasses;
            }
        };
    }
}
