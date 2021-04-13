package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.Deciders;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 测试Decider
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class DecidersTests extends ExceptionTests {
    @Test
    public void testNotNull() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(1);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.notNull());
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(null);
        expectMessage = "value是空";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.notNull());
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testIsNull() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(null);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.isNull());
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(1);
        expectMessage = "value不是空";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.isNull());
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testGreaterThan() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(1);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.greaterThan(0));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(0);
        expectMessage = "value:0需要大于0";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.greaterThan(0));
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testGreaterEqual() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.greaterEqual(0));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(-1);
        expectMessage = "value:-1需要大于等于0";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.greaterEqual(0));
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testLessThan() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(-1);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.lessThan(0));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(0);
        expectMessage = "value:0需要小于0";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.lessThan(0));
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testLessEqual() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.lessEqual(0));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(1);
        expectMessage = "value:1需要小于等于0";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.lessEqual(0));
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testBetween() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, 10));
        });
        assertThat(message, nullValue());

        // 测试合法值
        entity.setValue(10);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, 10));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(-1);
        expectMessage = "value:-1需要介于0（含）和10（含）之间";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, 10));
        });
        assertThat(message, is(expectMessage));

        // 测试非法值
        entity.setValue(11);
        expectMessage = "value:11需要介于0（含）和10（含）之间";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, 10));
        });
        assertThat(message, is(expectMessage));

        // 测试合法值
        entity.setValue(1);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, false, 10, false));
        });
        assertThat(message, nullValue());

        // 测试合法值
        entity.setValue(9);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, false, 10, false));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(0);
        expectMessage = "value:0需要介于0（不含）和10（不含）之间";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, false, 10, false));
        });
        assertThat(message, is(expectMessage));

        // 测试非法值
        entity.setValue(10);
        expectMessage = "value:10需要介于0（不含）和10（不含）之间";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.between(0, false, 10, false));
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testIs() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.is(0));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(1);
        expectMessage = "value:1不是0";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.is(0));
        });
        assertThat(message, is(expectMessage));
    }

    @Test
    public void testNot() {
        TestEntity entity = new TestEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.not(1));
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(1);
        expectMessage = "value:1是1";
        message = this.run(() -> {
            Checker.check(entity, TestEntity::getValue, Deciders.not(1));
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestEntity {
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

}
