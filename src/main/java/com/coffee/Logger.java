package com.coffee;

//日志类
public class Logger {
    private static final String MESSAGE = "MESSAGE: ";
    private static final String DEBUG = "DEBUG: ";
    private static final String ERROR = "ERROR: ";

    public static void error(String msg) {
        System.err.println(ERROR + msg);
    }

    public static void debug(String msg) {
        System.out.println(DEBUG + msg);
    }

    public static void message(String msg) {
        System.out.println(MESSAGE + msg);
    }
}
