package cn.coufran.doorgod.message;

import cn.coufran.doorgod.decider.Decider;
import cn.coufran.doorgod.decider.NotNullDecider;

/**
 * 消息模版生成器
 * @author Coufran
 * @since 1.0.0
 * @version 1.0.0
 */
public class MessageTemplateFactory {
    private static final String FIELD$NAME = FieldNameAndValueTemplateMessage.KEY_FIELD$NAME;
    private static final String VALUE = FieldNameAndValueTemplateMessage.KEY_VALUE;

    /**
     * 根据决策器生成不同的消息模版
     * @param decider 决策器
     * @return 消息模版
     */
    public static MessageTemplate createMessageTemplate(Decider decider) {
        if(decider instanceof NotNullDecider) {
            return new FormatStringMessageTemplate("%s是空", FIELD$NAME);
        } else {
            return new FormatStringMessageTemplate("%s:%s不合法", FIELD$NAME, VALUE);
        }
    }
}
