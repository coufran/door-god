package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;

public class MessageBuilderFactory {
    public static MessageBuilder createMessageBuilder(Decider decider) {
        return new MessageBuilder("${fieldName}是空");
    }
}
