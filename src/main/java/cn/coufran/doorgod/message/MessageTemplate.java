package cn.coufran.doorgod.message;

import java.util.Map;

/**
 * 消息模版
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class MessageTemplate {
    /**
     * 构造消息字符串
     * @param data 数据
     * @return 消息字符串
     */
    protected abstract String buildMessage(Map<String, Object> data);
}
