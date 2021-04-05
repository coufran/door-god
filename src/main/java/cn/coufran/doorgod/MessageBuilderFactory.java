package cn.coufran.doorgod;

public class MessageBuilderFactory {
    public static MessageBuilder createMessageBuilder(Decider decider) {
        return new MessageBuilder("${fieldName}是空");
    }
}
