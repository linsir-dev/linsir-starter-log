package com.linsir.log.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 20:02
 * @description：日志配置
 * @modified By：
 * @version: 0.0.1
 */
@ComponentScan("com.linsir.logRecord")
@Import({RabbitMqSenderConfiguration.class, RocketMqSenderConfiguration.class})
public class LogRecordAutoConfiguration {
}
