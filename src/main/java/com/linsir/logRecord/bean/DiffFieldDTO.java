package com.linsir.logRecord.bean;

import lombok.Data;

/**
 * @author ：linsir
 * @date ：Created in 2022/9/12 18:52
 * @description：不同的地方
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class DiffFieldDTO {
    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段别名
     */
    private String oldFieldAlias;

    /**
     * 字段别名
     */
    private String newFieldAlias;

    /**
     * 旧值
     */
    private Object oldValue;

    /**
     * 新值
     */
    private Object newValue;
}
