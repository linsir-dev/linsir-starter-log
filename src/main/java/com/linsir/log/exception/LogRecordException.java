package com.linsir.log.exception;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 20:21
 * @description：异常
 * @modified By：
 * @version:
 */
public class LogRecordException extends RuntimeException{
    public LogRecordException(String message, Throwable cause) {
        super(message, cause);
    }
}
