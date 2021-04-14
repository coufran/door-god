package cn.coufran.doorgod.reflect;

import cn.coufran.doorgod.Executor;
import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.message.*;

import java.util.List;

/**
 * 可被决策的结构元数据
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class DecidableMeta {
    /**
     * 获取决策注解元数据
     * @return 决策注解元数据
     */
    public abstract List<DecideAnnotationMeta> getDecideAnnotationMetas();

    /**
     * 获取决策值
     * @param entity 决策实体
     * @return 决策值
     */
    public abstract Object getValue(Object entity);

    /**
     * 获取决策消息
     * @param messageTemplate 消息模版
     * @return 决策值
     */
    public abstract Message getMessage(MessageTemplate messageTemplate, Object value);

    /**
     * 执行决策
     * @param executor 决策器
     * @param entity 待决策的实体
     */
    public void accept(Executor executor, Object entity) {
        List<DecideAnnotationMeta> decideAnnotationMetas = this.getDecideAnnotationMetas();
        for (DecideAnnotationMeta decideAnnotationMeta : decideAnnotationMetas) {
            // 获取值
            Object value = getValue(entity);
            // 获取决策器
            Decider decider = DeciderBuilder.buildDecider(decideAnnotationMeta);
            // 获取消息
            String annotationMessage = decideAnnotationMeta.getMessage();
            Message message;
            if(annotationMessage == null) { // 没有指定消息
                MessageTemplate messageTemplate = MessageTemplateFactory.createMessageTemplate(decider);
                message = getMessage(messageTemplate, value);
            } else { // 指定了消息
                message = new StringMessage(annotationMessage);
            }
            executor.execute(value, decider, message);
        }
    }


}
