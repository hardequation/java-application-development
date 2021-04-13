/**
 * jkgjdhkgjhdfkgjdhfk
 */
package com.acme.dbo.txlog;


import ooaddemo.controller.LoggerController;
import ooaddemo.domain.SeverityLevel;
import ooaddemo.message.*;

public class Facade {

    private static LoggerController controller;

    public static void setController(LoggerController loggerController){
        controller = loggerController;
    }

    public static void log(int message, SeverityLevel severityLevel) {
        controller.log(new IntegerMessage(message), severityLevel);
    }

    public static void log(byte message, SeverityLevel severityLevel) {
        controller.log(new ByteMessage(message), severityLevel);
    }

    public static void log(String message, SeverityLevel severityLevel) {
        controller.log(new StringMessage(message), severityLevel);
    }

    public static void log(int[] message, SeverityLevel severityLevel) {
        controller.log(new ArrayMessage(message), severityLevel);
    }

    public static void log(int[][] message, SeverityLevel severityLevel) {
        controller.log(new MatrixMessage(message), severityLevel);
    }

    public static void flush(SeverityLevel severityLevel) {
        controller.log(controller.getCurrentMessage(), severityLevel);
    }


}