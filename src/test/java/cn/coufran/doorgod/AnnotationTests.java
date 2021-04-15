package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.annotation.IsNull;
import cn.coufran.doorgod.decider.annotation.Min;
import cn.coufran.doorgod.decider.annotation.NotNull;
import cn.coufran.doorgod.group.Groups;
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

    /**
     * 消息注解测试
     */
    @Test
    public void testMessageAnnotation() {
        MessageEntity entity = new MessageEntity();

        String message;
        String exceptMessage;

        // 合法测试
        entity.setValue(SimpleEntity.LEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 非法测试
        exceptMessage = "请输入值";
        entity.setValue(SimpleEntity.ILLEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 基础注解测试实体
     */
    public static class MessageEntity {
        private static final String LEGAL_VALUE = "value";
        private static final String ILLEGAL_VALUE = null;

        @NotNull(message = "请输入值")
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 消息注解测试
     */
    @Test
    public void testGroupAnnotation() {
        GroupEntity entity = new GroupEntity();
        entity.setValue(null);

        String message;
        String exceptMessage;

        // 合法测试
        message = run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 非法测试
        exceptMessage = "value是空";
        entity.setValue(SimpleEntity.ILLEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity, "test");
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 基础注解测试实体
     */
    public static class GroupEntity {
        @NotNull(groups = "test")
        @IsNull(groups = Groups.DEFAULT)
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 消息注解测试
     */
    @Test
    public void testListAnnotation() {
        ListEntity entity = new ListEntity();

        String message;
        String exceptMessage;

        // 合法测试
        entity.setValue(ListEntity.LEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 非法测试
        exceptMessage = "not null 1";
        entity.setValue(SimpleEntity.ILLEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity, "1");
        });
        assertThat(message, is(exceptMessage));

        // 非法测试
        exceptMessage = "not null 2";
        entity.setValue(SimpleEntity.ILLEGAL_VALUE);
        message = run(() -> {
            Checker.check(entity, "2");
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 基础注解测试实体
     */
    public static class ListEntity {
        private static final String LEGAL_VALUE = "value";
        private static final String ILLEGAL_VALUE = null;

        @NotNull.List({
                @NotNull(message = "not null 1", groups = "1"),
                @NotNull(message = "not null 2", groups = "2")
        })
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
