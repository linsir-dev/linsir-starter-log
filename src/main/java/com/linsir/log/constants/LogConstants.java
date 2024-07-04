package com.linsir.log.constants;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 20:06
 * @description：日志类型
 * @modified By：
 * @version:
 */
public interface LogConstants {

    interface DataPipeline {

        String ROCKET_MQ = "rocketMq";

        String RABBIT_MQ = "rabbitMq";

        String STREAM = "stream";
    }
}
