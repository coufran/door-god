package cn.coufran.doorgod.message;

import cn.coufran.doorgod.decider.*;

/**
 * 消息模版生成器
 * @author Coufran
 * @since 1.0.0
 * @version 1.0.0
 */
public class MessageTemplateFactory {
    private static final String FIELD$NAME = "${" + FieldNameAndValueTemplateMessage.KEY_FIELD$NAME + "}";
    private static final String VALUE = "${" + FieldNameAndValueTemplateMessage.KEY_VALUE + "}";

    /**
     * 根据决策器生成不同的消息模版
     * @param decider 决策器
     * @return 消息模版
     */
    public static MessageTemplate createMessageTemplate(Decider decider) {
        if(decider instanceof NotNullDecider) {
            return new FormatStringMessageTemplate("%s是空", FIELD$NAME);
        } else if(decider instanceof ComparableDecider) {
            ComparableDecider comparableDecider = (ComparableDecider) decider;
            return createMessageTemplate(comparableDecider);
        } else if(decider instanceof IsNullDecider) {
            return new FormatStringMessageTemplate("%s不是空", FIELD$NAME);
        } else if(decider instanceof EqualDecider) {
            EqualDecider equalDecider = (EqualDecider) decider;
            Object compareValue = equalDecider.getCompareValue();
            return new FormatStringMessageTemplate("%s不是%s", FIELD$NAME, compareValue);
        } else if(decider instanceof NotEqualDecider) {
            NotEqualDecider notEqualDecider = (NotEqualDecider) decider;
            Object compareValue = notEqualDecider.getCompareValue();
            return new FormatStringMessageTemplate("%s是%s", FIELD$NAME, compareValue);
        } else {
            return new FormatStringMessageTemplate("%s:%s不合法", FIELD$NAME, VALUE);
        }
    }

    private static MessageTemplate createMessageTemplate(ComparableDecider comparableDecider) {
        Comparable min = comparableDecider.getMin();
        Comparable max = comparableDecider.getMax();
        boolean includeMin = comparableDecider.isIncludeMin();
        boolean includeMax = comparableDecider.isIncludeMax();
        String formatTemplate;
        if(max != null && min != null) {
            if(includeMax && includeMin) {
                formatTemplate = "%s:%s需要介于%s（含）和%s（含）之间";
            } else if(includeMax) {
                formatTemplate = "%s:%s需要介于%s（不含）和%s（含）之间";
            } else if(includeMin) {
                formatTemplate = "%s:%s需要介于%s（含）和%s（不含）之间";
            } else {
                formatTemplate = "%s:%s需要介于%s（不含）和%s（不含）之间";
            }
            return new FormatStringMessageTemplate(formatTemplate, FIELD$NAME, VALUE, min, max);
        } else if(max != null) {
            if(includeMax) {
                formatTemplate = "%s:%s需要小于等于%s";
            } else {
                formatTemplate = "%s:%s需要小于%s";
            }
            return new FormatStringMessageTemplate(formatTemplate, FIELD$NAME, VALUE, max);
        } else { // min != null
            if(includeMin) {
                formatTemplate = "%s:%s需要大于等于%s";
            } else {
                formatTemplate = "%s:%s需要大于等于%s";
            }
            return new FormatStringMessageTemplate(formatTemplate, FIELD$NAME, VALUE, min);
        }
    }
}
