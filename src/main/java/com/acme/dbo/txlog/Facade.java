/**
 * jkgjdhkgjhdfkgjdhfk
 */
package com.acme.dbo.txlog;


import ooaddemo.controller.LoggerController;
import ooaddemo.domain.SeverityLevel;
import ooaddemo.filter.SeverityMessageFilter;
import ooaddemo.message.*;
import ooaddemo.printer.ConsolePrinter;

public class Facade {


    private static DecoratingMessage currentMessage;

    private static final LoggerController controller = new LoggerController(
            new ConsolePrinter(),
            new SeverityMessageFilter(SeverityLevel.DEBUG)
    );

    public void log(int message, SeverityLevel severityLevel) {
        DecoratingMessage stringMessage = new IntegerMessage(message);
        log(stringMessage, severityLevel);
    }

    public void log(byte message, SeverityLevel severityLevel) {
        DecoratingMessage byteMessage = new ByteMessage(message);
        log(byteMessage, severityLevel);
    }

    public void log(String message, SeverityLevel severityLevel) {
        DecoratingMessage stringMessage = new StringMessage(message);
        log(stringMessage, severityLevel);
    }


    private void log(DecoratingMessage message, SeverityLevel severityLevel) {
        if (currentMessage == null) {
            currentMessage = message;
            return;
        }
        if (currentMessage.shouldFlush(message)) {
            controller.log(currentMessage, severityLevel);
            currentMessage = message;
            return;
        }
        currentMessage.add(message);
    }

    public  void log(int[] message, SeverityLevel severityLevel) {
        DecoratingMessage arrayMessage = new ArrayMessage(message);
        log(arrayMessage, severityLevel);
    }

    public  void log(int[][] message, SeverityLevel severityLevel) {
        DecoratingMessage matrixMessage = new MatrixMessage(message);
        log(matrixMessage, severityLevel);
    }



    public void flush(SeverityLevel severityLevel) {
        controller.log(currentMessage, severityLevel);
    }


}