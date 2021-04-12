package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.annotation.Min;
import cn.coufran.doorgod.decider.annotation.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 校验器测试
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class AnnotationTests extends ExceptionTests {
    /**
     * 基础注解测试
     */
    @Test
    public void testSimpleAnnotation() {
        SimpleEntity entity = new SimpleEntity();

        String message;
        String exceptMessage;

        // 合法测试
        entity.setValue(SimpleEntity.LEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 非法测试
        exceptMessage = "value是空";
        entity.setValue(SimpleEntity.ILLEGAL_VALUE);
        message = run(() -> {
           Checker.check(entity);
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 基础注解测试实体
     */
    public static class SimpleEntity {
        private static final String LEGAL_VALUE = "value";
        private static final String ILLEGAL_VALUE = null;

        @NotNull
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 属性注解测试
     */
    @Test
    public void testPropertyAnnotationDecider() {
        PropertyEntity entity = new PropertyEntity();

        String message;
        String exceptMessage;

        // 合法测试
        entity.setValue(PropertyEntity.LEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 非法测试
        exceptMessage = "value:"+PropertyEntity.ILLEGAL_VALUE+"需要大于等于1";
        entity.setValue(PropertyEntity.ILLEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 属性注解测试实体
     */
    public static class PropertyEntity {
        private static final int LEGAL_VALUE = 1;
        private static final int ILLEGAL_VALUE = 0;

        @Min(1)
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

}
