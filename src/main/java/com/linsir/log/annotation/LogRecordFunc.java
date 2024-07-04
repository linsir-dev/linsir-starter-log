package com.linsir.log.annotation;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 17:59
 * @description：操作日志
 * @modified By：
 * @version: 0.0.1
 */
public @interface LogRecordFunc {

    /**
     * 自定义函数的别名，如果为空即使用函数名
     */
    String value() default "";
}
