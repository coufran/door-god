package cn.coufran.doorgod.message;

import cn.coufran.doorgod.decider.Decider;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeciderMessageTemplate extends StringMessageTemplate {
    private Decider decider;

    public DeciderMessageTemplate(Decider decider) {
        super(null);
        this.decider = decider;
    }

    @Override
    public String getTemplate() {
        return MessageTemplateFactory.createMessageTemplate(decider);
    }
}
