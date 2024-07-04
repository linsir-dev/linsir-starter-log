package com.linsir.logRecord.annotation;

import java.lang.annotation.*;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 17:56
 * @description：操作日志注解
 * @modified By：
 * @version: 0.0.1
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(OperationLogs.class)
public @interface OperationLog {
    /**
     * 业务ID
     * 必填
     * SpEL表达式
     */
    String bizId();

    /**
     * 业务类型
     * 必填
     * SpEL表达式
     */
    String bizType();

    /**
     * 日志内容
     * 可选
     * SpEL表达式
     */
    String msg() default "";

    /**
     * 日志标签
     * 可选
     * SpEL表达式
     */
    String tag() default "'operation'";

    /**
     * 额外信息
     * 可选
     * SpEL表达式
     */
    String extra() default "";

    /**
     * 操作人ID
     * 可选
     * SpEL表达式
     */
    String operatorId() default "";

    /**
     * 切面执行时机
     * true: 执行方法前解析切面逻辑
     * false: 执行方法后解析切面逻辑
     */
    boolean executeBeforeFunc() default false;

    /**
     * 是否记录返回值
     * true: 记录返回值
     * false: 不记录返回值
     */
    boolean recordReturnValue() default false;

    /**
     * 日志记录条件
     * 可选
     * SpEL表达式
     */
    String condition() default "'true'";

    /**
     * 自定义方法执行是否成功 用于根据返回体或其他情况下自定义日志实体中的success字段
     * 可选
     * SpEL表达式
     */
    String success() default "";
}
