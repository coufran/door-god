package cn.coufran.doorgod;

import cn.coufran.doorgod.decider.annotation.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 测试Decider注解
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
public class DecideAnnotationTests extends ExceptionTests {
    @Test
    public void testNotNull() {
        TestNotNullEntity entity = new TestNotNullEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(1);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(null);
        expectMessage = "value是空";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestNotNullEntity {
        @NotNull
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    @Test
    public void testIsNull() {
        TestIsNullEntity entity = new TestIsNullEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(null);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(1);
        expectMessage = "value不是空";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestIsNullEntity {
        @IsNull
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    @Test
    public void testMax() {
        TestMaxEntity entity = new TestMaxEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        entity.setNotIncludeValue(-1);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(1);
        entity.setNotIncludeValue(-1);
        expectMessage = "value:1需要小于等于0";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));

        // 测试非法值
        entity.setValue(0);
        entity.setNotIncludeValue(0);
        expectMessage = "notIncludeValue:0需要小于0";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestMaxEntity {
        @Max(0)
        private Integer value;
        @Max(value = 0, include = false)
        private Integer notIncludeValue;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getNotIncludeValue() {
            return notIncludeValue;
        }

        public void setNotIncludeValue(Integer notIncludeValue) {
            this.notIncludeValue = notIncludeValue;
        }
    }

    @Test
    public void testMin() {
        TestMinEntity entity = new TestMinEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        entity.setNotIncludeValue(1);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(-1);
        entity.setNotIncludeValue(1);
        expectMessage = "value:-1需要大于等于0";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));

        // 测试非法值
        entity.setValue(0);
        entity.setNotIncludeValue(0);
        expectMessage = "notIncludeValue:0需要大于0";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestMinEntity {
        @Min(0)
        private Integer value;
        @Min(value = 0, include = false)
        private Integer notIncludeValue;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getNotIncludeValue() {
            return notIncludeValue;
        }

        public void setNotIncludeValue(Integer notIncludeValue) {
            this.notIncludeValue = notIncludeValue;
        }
    }

    @Test
    public void testBetween() {
        TestBetweenEntity entity = new TestBetweenEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        entity.setNotIncludeValue(1);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试合法值
        entity.setValue(10);
        entity.setNotIncludeValue(9);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(-1);
        entity.setNotIncludeValue(1);
        expectMessage = "value:-1需要介于0（含）和10（含）之间";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));

        // 测试非法值
        entity.setValue(0);
        entity.setNotIncludeValue(0);
        expectMessage = "notIncludeValue:0需要介于0（不含）和10（不含）之间";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));

        // 测试非法值
        entity.setValue(11);
        entity.setNotIncludeValue(9);
        expectMessage = "value:11需要介于0（含）和10（含）之间";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));

        // 测试非法值
        entity.setValue(10);
        entity.setNotIncludeValue(10);
        expectMessage = "notIncludeValue:10需要介于0（不含）和10（不含）之间";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestBetweenEntity {
        @Between(min = 0, max = 10)
        private Integer value;
        @Between(min = 0, includeMin = false, max = 10, includeMax = false)
        private Integer notIncludeValue;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getNotIncludeValue() {
            return notIncludeValue;
        }

        public void setNotIncludeValue(Integer notIncludeValue) {
            this.notIncludeValue = notIncludeValue;
        }
    }

    @Test
    public void testIs() {
        TestIsEntity entity = new TestIsEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(0);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(1);
        expectMessage = "value:1不是0";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestIsEntity {
        @Is(0)
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    @Test
    public void testNot() {
        TestNotEntity entity = new TestNotEntity();

        String expectMessage;
        String message;

        // 测试合法值
        entity.setValue(1);
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, nullValue());

        // 测试非法值
        entity.setValue(0);
        expectMessage = "value:0是0";
        message = this.run(() -> {
            Checker.check(entity);
        });
        assertThat(message, is(expectMessage));
    }

    /**
     * 测试实体
     */
    public class TestNotEntity {
        @Not(0)
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

}
