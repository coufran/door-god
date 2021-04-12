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

    //TODO other decider tests

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
