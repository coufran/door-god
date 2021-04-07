package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.decider.Decider;

import java.util.List;

/**
 * 可被决策的结构元数据
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class DecidableMeta {
    /**
     * 获取决策器
     * @return 决策器
     */
    public abstract List<Decider<?>> getDeciders();

}
