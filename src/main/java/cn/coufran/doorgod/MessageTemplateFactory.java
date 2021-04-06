package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Decider;

public class MessageTemplateFactory {
    public static String createMessageTemplate(Decider decider) {
        return "${fieldName}是空";
    }
}
