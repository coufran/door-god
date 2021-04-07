package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Deciders;
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
public class CheckerTests extends ExceptionTests {
    /**
     * 自定义决策规则测试
     */
    @Test
    public void testCustomerDecider() {
        String message;
        String exceptMessage;

        // 基础校验
        message = run(() -> {
            Checker.check(() -> false);
        });
        assertThat(message, notNullValue());

        // 带message的校验
        exceptMessage = "测试：未通过校验";
        message = run(() -> {
            Checker.check(() -> false, "测试：未通过校验");
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 值校验测试
     */
    @Test
    public void testSimpleDecider() {
        String message;
        String exceptMessage;

        // 基础校验
        message = run(() -> {
            Checker.check(null, Deciders.notNull());
        });
        assertThat(message, notNullValue());

        // 带message的校验
        exceptMessage = "测试：未通过校验";
        message = run(() -> {
            Checker.check(null, Deciders.notNull(), "测试：未通过校验");
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 字段校验测试
     */
    @Test
    public void testFieldDecider() {
        Entity entity = new Entity();
        entity.setValue(Entity.ILLEGAL_VALUE);

        String message;
        String exceptMessage;

        exceptMessage = "value是空";
        message = run(() -> {
            Checker.check(entity, Entity::getValue, Deciders.notNull());
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 实体校验测试
     */
    @Test
    public void testEntityDecider() {
        Entity entity = new Entity();
        entity.setValue(Entity.ILLEGAL_VALUE);

        String message;
        String exceptMessage;

        exceptMessage = "value是空";
        message = run(() -> {
           Checker.check(entity);
        });
        assertThat(message, is(exceptMessage));
    }

    /**
     * 测试实体
     */
    public static class Entity {
        private static final String LEGAL_VALUE = "value";
        private static final String ILLEGAL_VALUE = null;

        @NotNull
        private String value = LEGAL_VALUE;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
