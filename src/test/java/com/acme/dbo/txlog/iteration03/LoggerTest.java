package com.acme.dbo.txlog.iteration03;

import com.acme.dbo.txlog.Facade;
import com.acme.dbo.txlog.SysoutCaptureAndAssertionAbility;
import ooaddemo.domain.SeverityLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    Facade facade = new Facade();

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion


    @Test
    public void shouldLogIntegersArray() throws IOException {
        //region when
        facade.log(new int[]{-1, 0, 1}, SeverityLevel.WARNING);
        facade.flush(SeverityLevel.WARNING);
        //endregion

        //region then
        assertSysoutEquals(
                "primitives array: {-1, 0, 1}\r\n"
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        facade.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}}, SeverityLevel.WARNING);
        facade.flush(SeverityLevel.WARNING);
        //endregion

        //region then
        assertSysoutEquals(
                "primitives matrix: {\r\n" +
                        "{-1, 0, 1}\r\n" +
                        "{1, 2, 3}\r\n" +
                        "{-1, -2, -3}\r\n" +
                        "}\r\n"
        );
        //endregion
    }
}

//    @Test
//    public void shouldLogStringsWithOneMethodCall() throws IOException {
//        //region when
//        facade.log("str1", "string 2", "str 3");
//        //endregion
//
//        //region then
//        assertSysoutContains("str1\nstring 2\nstr 3");
//        //endregion
//    }

//    @Test
//    public void shouldLogIntegersWithOneMethodCall() throws IOException {
//        //region when
//        Facade.log(-1, 0, 1, 3);
//        //endregion
//
//        //region then
//        assertSysoutContains("3");
//        //endregion
//    }
//    */
//}