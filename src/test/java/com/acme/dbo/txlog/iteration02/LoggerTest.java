package com.acme.dbo.txlog.iteration02;

import com.acme.dbo.txlog.Facade;
import com.acme.dbo.txlog.SysoutCaptureAndAssertionAbility;
import ooaddemo.domain.SeverityLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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


    //TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {

        facade.log("str 1", SeverityLevel.WARNING);
        facade.log(1, SeverityLevel.WARNING);
        facade.log(2, SeverityLevel.WARNING);
        facade.log("str 2", SeverityLevel.WARNING);
        facade.log(0, SeverityLevel.WARNING);
        facade.flush(SeverityLevel.WARNING);
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("3");
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        //endregion
    }


    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        facade.log("str 1", SeverityLevel.WARNING);
        facade.log(10, SeverityLevel.WARNING);
        facade.log(Integer.MAX_VALUE, SeverityLevel.WARNING);
        facade.log("str 2", SeverityLevel.WARNING);
        facade.log(0, SeverityLevel.WARNING);
        facade.flush(SeverityLevel.WARNING);


        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("10");
        assertSysoutContains("" + Integer.MAX_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");

        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        facade.log("str 1", SeverityLevel.WARNING);
        facade.log((byte) 10, SeverityLevel.WARNING);
        facade.log((byte) Byte.MAX_VALUE, SeverityLevel.WARNING);
        facade.log("str 2", SeverityLevel.WARNING);
        facade.log(0, SeverityLevel.WARNING);
        facade.flush(SeverityLevel.WARNING);
        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("10");
        assertSysoutContains("" + Byte.MAX_VALUE);
        assertSysoutContains("str 2");
        assertSysoutContains("0");

        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        facade.log("str 1", SeverityLevel.WARNING);
        facade.log("str 2", SeverityLevel.WARNING);
        facade.log("str 2", SeverityLevel.WARNING);
        facade.log(0, SeverityLevel.WARNING);
        facade.log("str 2", SeverityLevel.WARNING);
        facade.log("str 3", SeverityLevel.WARNING);
        facade.log("str 3", SeverityLevel.WARNING);
        facade.log("str 3", SeverityLevel.WARNING);
        facade.flush(SeverityLevel.WARNING);
        //endregion

        //region then

        assertSysoutContains("str 1");
        assertSysoutContains("str 2 (x2)");
        assertSysoutContains("0");
        assertSysoutContains("str 2");
        assertSysoutContains("str 3 (x3)");

        //endregion
    }


}