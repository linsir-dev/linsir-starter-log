package com.linsir.log.service;


import com.linsir.log.bean.LogDTO;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 19:26
 * @description：日志接口
 * @modified By：
 * @version:
 */
public interface IOperationLogGetService {
    /**
     * 自定义日志监听
     * @param logDTO 日志传输实体
     */
    void createLog(LogDTO logDTO);
}
