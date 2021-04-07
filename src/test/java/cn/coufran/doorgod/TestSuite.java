package cn.coufran.doorgod;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 测试集
 * @author Coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CheckerTests.class,
        AnnotationTests.class
})
public class TestSuite {
}
