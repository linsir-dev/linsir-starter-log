package com.linsir.logRecord.thread;

import com.linsir.logRecord.configuration.LogRecordProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 18:55
 * @description：日志线程池
 * @modified By：
 * @version: 0.0.1
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "log-record.thread-pool.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({LogRecordProperties.class})
public class LogRecordThreadPool {

    private static final ThreadFactory THREAD_FACTORY = new CustomizableThreadFactory("log-record-");

    private final ExecutorService LOG_RECORD_POOL_EXECUTOR;

    public LogRecordThreadPool(LogRecordProperties logRecordProperties) {
        log.info("LogRecordThreadPool init poolSize [{}]", logRecordProperties.getThreadPool().getPoolSize());
        int poolSize = logRecordProperties.getThreadPool().getPoolSize();
        this.LOG_RECORD_POOL_EXECUTOR = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024), THREAD_FACTORY, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public ExecutorService getLogRecordPoolExecutor() {
        return LOG_RECORD_POOL_EXECUTOR;
    }

}
