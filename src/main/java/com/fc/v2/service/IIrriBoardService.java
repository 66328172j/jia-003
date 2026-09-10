package com.fc.v2.service;

import java.util.List;
import java.util.Map;

/**
 * 移动端墒情看板 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface IIrriBoardService {

    /**
     * 按地块编号查询看板信息
     */
    public Map<String, Object> board(String plotCode);

    /**
     * 最近采集趋势
     */
    public List<Map<String, Object>> recent(String plotCode);

    /**
     * 待执行灌溉任务
     */
    public List<Map<String, Object>> todoTasks(String plotCode);
}
