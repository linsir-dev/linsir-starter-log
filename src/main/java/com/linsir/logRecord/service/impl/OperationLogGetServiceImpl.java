package com.linsir.logRecord.service.impl;


import com.linsir.base.core.util.JSON;
import com.linsir.logRecord.bean.LogDTO;
import com.linsir.logRecord.service.IOperationLogGetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 16:49
 * @description：本地日志实现
 * @modified By：
 * @version: 0.0.1
 */
@Slf4j
@Service
public class OperationLogGetServiceImpl implements IOperationLogGetService {
    @Override
    public void createLog(LogDTO logDTO) {
        log.info("logDTO: [{}]", JSON.toJSONString(logDTO));
    }
}
