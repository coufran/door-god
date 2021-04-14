package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.reflect.util.ClassUtils;
import cn.coufran.doorgod.reflect.util.MethodUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 决策器构造器
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeciderBuilder {
    /**
     * 构造决策器
     * @param decideAnnotationMeta 决策注解元数据
     * @return 决策器
     */
    public static Decider<?> buildDecider(DecideAnnotationMeta decideAnnotationMeta) {
        // 构造对象
        Class<? extends Decider> deciderClass = decideAnnotationMeta.getDeciderClass();
        Decider<?> decider = ClassUtils.newInstance(deciderClass);
        // 设置参数
        Map<String, Object> properties = decideAnnotationMeta.getProperties();
        for(Map.Entry<String, Object> property : properties.entrySet()) {
            String propertyName = property.getKey();
            Object propertyValue = property.getValue();
            // 查找setter
            String setterName = MethodUtils.getSetterNameByPropertyName(propertyName);
            List<Method> setters = ClassUtils.getMethods(deciderClass, setterName);
            if(setters.isEmpty()) {
                throw new ReflectException("找不到setter");
            }
            if(setters.size() > 1) {
                throw new ReflectException("找到多个setter");
            }
            Method setter = setters.get(0);
            // 设置注解值
            MethodUtils.invoke(setter, decider, propertyValue);
        }
        return decider;
    }
}
