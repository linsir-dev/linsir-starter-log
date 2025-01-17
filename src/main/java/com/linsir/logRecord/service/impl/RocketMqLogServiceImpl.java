package com.linsir.logRecord.service.impl;


import com.alibaba.fastjson.JSON;
import com.linsir.logRecord.bean.LogDTO;
import com.linsir.logRecord.configuration.LogRecordProperties;
import com.linsir.logRecord.constants.LogConstants;
import com.linsir.logRecord.service.LogService;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.ROCKET_MQ)
public class RocketMqLogServiceImpl implements LogService {

    @Autowired
    private LogRecordProperties properties;

    @Autowired
    private DefaultMQProducer defaultMqProducer;

    @Override
    public boolean createLog(LogDTO logDTO) {
        try {
            Message msg = new Message(properties.getRocketMqProperties().getTopic(), properties.getRocketMqProperties().getTag(), (JSON.toJSONString(logDTO)).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = defaultMqProducer.send(msg);
            log.info("LogRecord RocketMq send LogDTO [{}] sendResult: [{}]", logDTO, sendResult);
            return true;
        } catch (Exception e) {
            log.error("LogRecord RocketMq send LogDTO error", e);
            return false;
        }
    }
}
