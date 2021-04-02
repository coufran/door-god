package cn.coufran.doorgod;

import cn.coufran.doorgod.annotation.Check;
import cn.coufran.doorgod.decider.annotation.NotNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckerTests extends ExceptionTests {
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

    @Test
    public void testFieldDecider() {
        Entity entity = new Entity();

        String message;
        String exceptMessage;

        exceptMessage = "value为空";
        message = run(() -> {
            Checker.check(entity, Entity::getValue, Deciders.notNull());
        });
        assertThat(message, is(exceptMessage));
    }

    @Test
    public void testEntityDecider() {
        Entity entity = new Entity();

        String message;
        String exceptMessage;

        exceptMessage = "value为空";
        message = run(() -> {
           Checker.check(entity);
        });
        assertThat(message, is(exceptMessage));
    }

    public static class Entity {
        @NotNull
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
