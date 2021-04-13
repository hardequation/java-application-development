package com.acme.dbo.txlog.iteration02;

import com.acme.dbo.txlog.Facade;
import com.acme.dbo.txlog.SysoutCaptureAndAssertionAbility;
import ooaddemo.controller.LoggerController;
import ooaddemo.domain.SeverityLevel;
import ooaddemo.filter.SeverityMessageFilter;
import ooaddemo.printer.ConsolePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        Facade.setController(new LoggerController(
                new ConsolePrinter(),
                new SeverityMessageFilter(SeverityLevel.DEBUG)));
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

        Facade.log("str 1", SeverityLevel.WARNING);
        Facade.log(1, SeverityLevel.WARNING);
        Facade.log(2, SeverityLevel.WARNING);
        Facade.log("str 2", SeverityLevel.WARNING);
        Facade.log(0, SeverityLevel.WARNING);
        Facade.flush(SeverityLevel.WARNING);
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
        Facade.log("str 1", SeverityLevel.WARNING);
        Facade.log(10, SeverityLevel.WARNING);
        Facade.log(Integer.MAX_VALUE, SeverityLevel.WARNING);
        Facade.log("str 2", SeverityLevel.WARNING);
        Facade.log(0, SeverityLevel.WARNING);
        Facade.flush(SeverityLevel.WARNING);


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
        Facade.log("str 1", SeverityLevel.WARNING);
        Facade.log((byte) 10, SeverityLevel.WARNING);
        Facade.log((byte) Byte.MAX_VALUE, SeverityLevel.WARNING);
        Facade.log("str 2", SeverityLevel.WARNING);
        Facade.log(0, SeverityLevel.WARNING);
        Facade.flush(SeverityLevel.WARNING);
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
        Facade.log("str 1", SeverityLevel.WARNING);
        Facade.log("str 2", SeverityLevel.WARNING);
        Facade.log("str 2", SeverityLevel.WARNING);
        Facade.log(0, SeverityLevel.WARNING);
        Facade.log("str 2", SeverityLevel.WARNING);
        Facade.log("str 3", SeverityLevel.WARNING);
        Facade.log("str 3", SeverityLevel.WARNING);
        Facade.log("str 3", SeverityLevel.WARNING);
        Facade.flush(SeverityLevel.WARNING);
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