package cn.coufran.doorgod;

import org.junit.Test;

public class CheckerTests {

    @Test
    public void testCheck() {
        Checker.check("", Deciders.notNull());
    }

}
