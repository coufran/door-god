package cn.coufran.doorgod;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckerTests {

    @Test
    public void testCheck() {
        String value = null;
        String message = null;
        try {
            Checker.check(value, Deciders.notNull());
        } catch (ValidateException e) {
            message = e.getMessage();
        }
        assertThat(message, is("未通过校验"));
    }

    @Test
    public void testCheckAuthMessage() {
        TestEntity testEntity = new TestEntity(1);
        String message = null;
        try {
            Checker.check(testEntity, TestEntity::getTestValue, Deciders.isNull());
        } catch (ValidateException e) {
            message = e.getMessage();
        }
        assertThat(message, is("testValue是空"));
    }

}
