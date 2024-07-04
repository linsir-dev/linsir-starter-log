package com.linsir.logRecord.service;


import com.linsir.logRecord.bean.LogDTO;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 19:25
 * @description：日志接口
 * @modified By：
 * @version: 0.0.1
 */
public interface LogService {

    /**
     * 自定义日志监听
     * @param logDTO 日志传输实体
     */
    boolean createLog(LogDTO logDTO);

}
