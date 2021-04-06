package cn.coufran.doorgod.message;

import java.util.Map;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class MessageTemplate {
    protected abstract String buildMessage(Map<String, Object> data);
}
