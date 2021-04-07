package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 扫描器，用于扫描POJO类、方法、属性等可被判定的结构
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Scanner<T> {
    /**
     * 扫描结构，并返回结构元数据
     * @param t 被扫描结构
     * @return 结构元数据
     */
    public abstract DecidableMeta scan(T t);

    /**
     * 将决策注解转换为决策器
     * @param annotations 决策注解
     * @return 决策器
     */
    public List<Decider<?>> parseDecider(Annotation[] annotations) {
        return AnnotationsScanner.getInstance()
                .scan(annotations)
                .getDeciders();
    }
}
