package com.linsir.log.configuration;

import com.linsir.log.constants.LogConstants;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
// org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 20:07
 * @description：流的方式日志
 * @modified By：
 * @version:
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "log-record.data-pipeline", havingValue = LogConstants.DataPipeline.STREAM)
@EnableConfigurationProperties({LogRecordProperties.class})
@ConditionalOnClass(BindingServiceConfiguration.class)
@AutoConfigureBefore({BindingServiceConfiguration.class})

//TODO 新版本升级 注解不在了
//@EnableBinding(StreamSenderConfiguration.LogRecordChannel.class)
public class StreamSenderConfiguration {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    private final LogRecordProperties properties;

    private final BindingServiceProperties bindings;

    public StreamSenderConfiguration(BindingServiceProperties bindings, LogRecordProperties logRecordProperties) {
        this.properties = logRecordProperties;
        this.bindings = bindings;
    }

    @PostConstruct
    public void init() {
        BindingProperties inputBinding = this.bindings.getBindings().get(LogRecordChannel.OUTPUT);
        if (inputBinding == null) {
            this.bindings.getBindings().put(LogRecordChannel.OUTPUT, new BindingProperties());
        }

        BindingProperties input = this.bindings.getBindings().get(LogRecordChannel.OUTPUT);
        if (input.getDestination() == null || input.getDestination().equals(LogRecordChannel.OUTPUT)) {
            input.setDestination(Optional.ofNullable(properties.getStream().getDestination()).orElse("stream_logging_" + applicationName + "_" + activeProfile ));
        }
        if (!StringUtils.hasText(input.getGroup())) {
            input.setGroup(Optional.ofNullable(properties.getStream().getGroup()).orElse(applicationName));
        }

        if (StringUtils.hasText(properties.getStream().getBinder())) {
            input.setBinder(properties.getStream().getBinder());
        }


    }

    public interface LogRecordChannel {

        String OUTPUT = "LogRecordChannel";

        /**
         * 日志输出
         * @return SubscribableChannel messageLoggingQueueInput();
         */

        //TODO 新版本升级 注解不在了
        //@Output(OUTPUT)
        MessageChannel messageLoggingQueueInput();

    }
}
