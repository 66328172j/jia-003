package com.fc.v2.service;

import com.fc.v2.model.custom.ImportResult;

import java.util.List;

/**
 * 墒情批量导入 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface IIrriImportService {

    /**
     * 导入墒情数据
     *
     * @param rows 行数据（每行：监测点编号/采集时间/含水量/土温）
     * @return 导入结果
     */
    public ImportResult importMoisture(List<String[]> rows);
}
