package cn.coufran.doorgod.message;

import cn.coufran.doorgod.decider.Decider;

/**
 * 消息模版生成器
 * @author Coufran
 * @since 1.0.0
 * @version 1.0.0
 */
public class MessageTemplateFactory {
    /**
     * 根据决策器生成不同的消息模版
     * @param decider 决策器
     * @return 消息模版
     */
    public static String createMessageTemplate(Decider decider) {
        String fieldNameKey = FieldNameAndValueTemplateMessage.KEY_FIELD$NAME;
        return String.format("${%s}是空", fieldNameKey);
    }
}
