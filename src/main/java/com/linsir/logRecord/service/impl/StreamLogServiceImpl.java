package com.linsir.logRecord.service.impl;


import com.linsir.logRecord.bean.LogDTO;
import com.linsir.logRecord.configuration.LogRecordProperties;
import com.linsir.logRecord.configuration.StreamSenderConfiguration;
import com.linsir.logRecord.constants.LogConstants;
import com.linsir.logRecord.service.LogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.STREAM)
public class StreamLogServiceImpl implements LogService {

    @Resource
    private StreamSenderConfiguration.LogRecordChannel channel;

    @Override
    public boolean createLog(LogDTO logDTO) {
        return channel.messageLoggingQueueInput().send(MessageBuilder.withPayload(logDTO).build());
    }
}
