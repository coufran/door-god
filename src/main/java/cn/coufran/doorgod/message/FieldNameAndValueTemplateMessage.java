package cn.coufran.doorgod.message;

import java.util.Map;

/**
 * @author liuhm8
 * @version 1.0.0
 * @since 1.0.0
 */
public class FieldNameAndValueTemplateMessage extends TemplateMessage {
    protected static final String KEY_FIELD$NAME = "fieldName";
    protected static final String KEY_VALUE = "value";
    private String fieldName;
    private Object value;

    public FieldNameAndValueTemplateMessage(String template) {
        super(template);
    }

    public FieldNameAndValueTemplateMessage setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public FieldNameAndValueTemplateMessage setValue(Object value) {
        this.value = value;
        return this;
    }

    public Object getValue() {
        return value;
    }

    @Override
    protected Map<String, Object> getData() {
        Map<String, Object> data = super.getData();
        data.put(KEY_FIELD$NAME, getFieldName());
        data.put(KEY_VALUE, getValue());
        return data;
    }
}
