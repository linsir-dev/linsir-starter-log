package com.linsir.logRecord.annotation;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 18:00
 * @description：操作日志不同之处
 * @modified By：
 * @version: 0.0.1
 */
public @interface LogRecordDiff {
    /**
     * 类/字段的别名 不填则默认类/字段名
     */
    String alias() default "";
}
