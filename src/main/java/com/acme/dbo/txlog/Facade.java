/**
 * jkgjdhkgjhdfkgjdhfk
 */
package com.acme.dbo.txlog;


import ooaddemo.controller.LoggerController;
import ooaddemo.domain.SeverityLevel;
import ooaddemo.filter.SeverityMessageFilter;
import ooaddemo.message.ByteMessage;
import ooaddemo.message.IntegerMessage;
import ooaddemo.message.StringMessage;
import ooaddemo.printer.ConsolePrinter;

import java.util.Arrays;

public class Facade {
    public static final String PRIMITIVE_PREFIX = "primitive: ";
    public static final String STRING_PREFIX = "string: ";
    public static final String ARRAY_PREFIX = "primitives array: ";
    public static final String MATRIX_PREFIX = "primitives matrix: ";
    private static String currentType = null;
    private static String stringMessageBody = null;
    private static Integer duplicateMessageCount = null;
    private static Integer numberAccumulator = null;

    private static LoggerController controller = new LoggerController(
        new ConsolePrinter(),
        new SeverityMessageFilter(SeverityLevel.DEBUG)
    );


//move current type updating and call subject accumulator in log
    public void log(int message, SeverityLevel severityLevel) {
        controller.log(new IntegerMessage(message), severityLevel);
        //updateCurrentType("Integer");
        //numberAccumulatorStrategy((Number) message);
    }

    public void log(byte message, SeverityLevel severityLevel) {
        controller.log(new IntegerMessage(message), severityLevel);
//        updateCurrentType("Byte");
//        numberAccumulatorStrategy((Number) message);
    }

    public void log(Byte message, SeverityLevel severityLevel) {
        controller.log(new ByteMessage(message), severityLevel);
//        updateCurrentType("String");
//        stringAccumulatorStrategy((String) message);
    }

    public static void log(int[] message) {
        updateCurrentType("int[]");
        arrayStrategy(message);
    }

    public static void log(int[][] message) {
        updateCurrentType("int[][]");
        arrayStrategy(message);
    }

    private static void updateCurrentType(String type) {
        if (!type.equals(currentType)) {
            currentType = type;
            flush();
        }
    }

    private static void stringAccumulatorStrategy(String message) {
        if (stringMessageBody != null && stringMessageBody.equals(message)) {
            duplicateMessageCount++;
            return;
        }
        flush();
        stringMessageBody = message;
        duplicateMessageCount = 1;

    }

    private static void numberAccumulatorStrategy(Number message) {
        int maxValue = 0;
        int logMessage = message.intValue();
//add 'else' branch and divide in two methods
        if (message instanceof Integer) {
            maxValue = Integer.MAX_VALUE;
        }
        if (message instanceof Byte) {
            maxValue = Byte.MAX_VALUE;
        }
//move to additional method
        if (message.intValue() == maxValue) {
            flush();
            numberAccumulator = logMessage;
            flush();
            return;
        }

        if (numberAccumulator == null) {
            numberAccumulator = logMessage;

        } else {
            numberAccumulator += logMessage;
        }
    }

    private static void arrayStrategy(int[] message) {
        logToConsolePure(ARRAY_PREFIX, "");
        printArrayToConsole(message);
    }

    private static void arrayStrategy(int[][] message) {
        logToConsolePure(MATRIX_PREFIX, "");
        StringBuilder result = new StringBuilder();
        logToConsole("{", "");
        for (int[] elem : message) {
            printArrayToConsole(elem);
        }
        logToConsole("}", "");
        String endMessage = result.toString();
        logToConsolePure(endMessage, "");
    }

    private static void printArrayToConsole(int[] arrayMessage) {
        logToConsole("", Arrays.toString(arrayMessage).replace('[', '{').replace(']', '}'));
    }

    private static void logToConsole(String prefix, String message) {
        System.out.println(prefix + message);
    }

    private static void logToConsolePure(String prefix, String message) {
        System.out.print((prefix + message));
    }

    public static void flush() {
        //move fluch() in subject message
        stringOutputStrategy();
        numberOutputStrategy();
        stringMessageBody = null;
        duplicateMessageCount = null;
        numberAccumulator = null;
    }

    public static void stringOutputStrategy() {
        String postfix = "";
        if (stringMessageBody != null) {
            if (duplicateMessageCount > 1) {
                postfix = " (x" + duplicateMessageCount + ")";
            }
            logToConsole(STRING_PREFIX, stringMessageBody + postfix);
        }
    }

    public static void numberOutputStrategy() {
        if (numberAccumulator != null) {
            logToConsole(PRIMITIVE_PREFIX, numberAccumulator.toString());
        }
    }

}