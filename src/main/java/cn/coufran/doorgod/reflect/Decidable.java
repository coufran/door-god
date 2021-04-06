package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.util.List;

/**
 * 可被决策的结构
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Decidable {
    /**
     * 获取决策策略
     * @return 决策策略
     */
    List<Class<? extends Decider>> getDeciderClasses();

}
