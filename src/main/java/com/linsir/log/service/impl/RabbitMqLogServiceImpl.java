package com.linsir.log.service.impl;


import com.alibaba.fastjson.JSON;
import com.linsir.log.bean.LogDTO;
import com.linsir.log.configuration.LogRecordProperties;
import com.linsir.log.constants.LogConstants;
import com.linsir.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.RABBIT_MQ)
public class RabbitMqLogServiceImpl implements LogService {

    @Autowired
    private RabbitTemplate rubeExchangeTemplate;

    @Autowired
    private LogRecordProperties properties;

    @Override
    public boolean createLog(LogDTO logDTO) {
        log.info("LogRecord RabbitMq ready to send routingKey [{}] LogDTO [{}]", properties.getRabbitMqProperties().getRoutingKey(), logDTO);
        rubeExchangeTemplate.convertAndSend(properties.getRabbitMqProperties().getRoutingKey(), JSON.toJSONString(logDTO));
        return true;
    }
}
