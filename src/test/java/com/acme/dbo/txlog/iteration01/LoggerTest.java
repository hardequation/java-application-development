package com.acme.dbo.txlog.iteration01;

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

import static java.lang.System.lineSeparator;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    Facade facade;

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

    @Test
    public void shouldLogInteger() throws IOException {

        //region when
        Facade.log(1, SeverityLevel.WARNING);
        Facade.log(0, SeverityLevel.WARNING);
        Facade.log(-1, SeverityLevel.WARNING);
        Facade.flush(SeverityLevel.WARNING);
        //endregion

        //region then
        assertSysoutContains("primitive: 1");
        assertSysoutContains("primitive: 0");
        assertSysoutContains("primitive: -1");
        //assertSysoutEquals("primitive: 1" + lineSeparator() + "primitive: 0\nprimitive: -1\n");
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        Facade.log((byte) 1, SeverityLevel.WARNING);
        Facade.log((byte) 0, SeverityLevel.WARNING);
        Facade.log((byte) -1, SeverityLevel.WARNING);
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("1");
        assertSysoutContains("0");
        assertSysoutContains("-1");
        //endregion
    }


//    @Test
//    public void shouldLogChar() throws IOException {
//        //region when
//        facade.log('a', SeverityLevel.WARNING);
//        facade.log('b', SeverityLevel.WARNING);
//        //endregion
//
//        //region then
//        assertSysoutContains("char: ");
//        assertSysoutContains("a");
//        assertSysoutContains("b");
//        //endregion
//    }


    @Test
    public void shouldLogString() throws IOException {
        //region when
        facade.log("test string 1", SeverityLevel.WARNING);
        facade.log("other str", SeverityLevel.WARNING);

        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

//    @Test
//    public void shouldLogBoolean() throws IOException {
//        //region when
//        Facade.log(true);
//        Facade.log(false);
//        //endregion
//
//        //region then
//        assertSysoutContains("primitive: ");
//        assertSysoutContains("true");
//        assertSysoutContains("false");
//        //endregion
//    }
//
//    @Test
//    public void shouldLogReference() throws IOException {
//        //region when
//        Facade.log(new Object());
//        //endregion
//
//        //region then
//        assertSysoutContains("reference: ");
//        assertSysoutContains("@");
//        //endregion
//    }


}