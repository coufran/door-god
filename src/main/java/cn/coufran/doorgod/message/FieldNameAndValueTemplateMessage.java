package cn.coufran.doorgod.message;

import java.util.Map;

/**
 * <p>
 *     包含字段名和值的模版消息
 * </p>
 * <p>
 *     用法如下：
 *     <pre>
 * Message message = new FieldNameAndValueTemplateMessage("${fieldName}:${value}未通过校验")
 *                       .setFieldName("testField")
 *                       .setValue(1);
 *     </pre>
 * </p>
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class FieldNameAndValueTemplateMessage extends TemplateMessage {
    /** 模版Key值：字段名 */
    protected static final String KEY_FIELD$NAME = "fieldName";
    /** 模版Key值：值 */
    protected static final String KEY_VALUE = "value";

    /** 字段名 */
    private String fieldName;
    /** 值 */
    private Object value;

    /**
     * 构造模版消息
     * @param messageTemplate 消息模版，至少包含{@link FieldNameAndValueTemplateMessage#KEY_FIELD$NAME}
     *                 和{@link FieldNameAndValueTemplateMessage#KEY_VALUE}两个空白
     */
    public FieldNameAndValueTemplateMessage(MessageTemplate messageTemplate) {
        super(messageTemplate);
    }

    /**
     * 设置字段名
     * @param fieldName 字段名
     * @return 当前对象
     */
    public FieldNameAndValueTemplateMessage setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * 获取字段名
     * @return 字段名
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * 设置值
     * @param value 值
     * @return 当前对象
     */
    public FieldNameAndValueTemplateMessage setValue(Object value) {
        this.value = value;
        return this;
    }

    /**
     * 获取值
     * @return 值
     */
    public Object getValue() {
        return value;
    }

    /**
     * 获取数据对象，数据对象中包含字段名和值两组数据
     */
    @Override
    protected Map<String, Object> getData() {
        Map<String, Object> data = super.getData();
        data.put(KEY_FIELD$NAME, getFieldName());
        data.put(KEY_VALUE, getValue());
        return data;
    }
}
