package com.linsir.logRecord.annotation;

import java.lang.annotation.*;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 17:58
 * @description：操作日志注解
 * @modified By：
 * @version: 0.0.1
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogs {

    OperationLog[] value();
}
