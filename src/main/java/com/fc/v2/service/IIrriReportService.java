package com.fc.v2.service;

import java.util.List;
import java.util.Map;

/**
 * 灌溉统计报表 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface IIrriReportService {

    /**
     * 按灌区统计用水量
     */
    public List<Map<String, Object>> waterStat(String begin, String end);

    /**
     * 用水明细
     */
    public List<Map<String, Object>> waterDetail(String begin, String end);

    /**
     * 按灌区统计任务完成情况
     */
    public List<Map<String, Object>> taskStat(String begin, String end);

    /**
     * 待执行任务数
     */
    public long todoCount();

    /**
     * 墒情等级分布
     */
    public List<Map<String, Object>> moistureDistribution();
}
