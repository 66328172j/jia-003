package com.fc.v2.service.impl;

import com.fc.v2.service.IIrriReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 灌溉统计报表 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class IrriReportServiceImpl implements IIrriReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> waterStat(String begin, String end) {
        if (begin == null || begin.isEmpty() || end == null || end.isEmpty()) {
            throw new RuntimeException("统计时间范围不能为空");
        }
        String sql = "SELECT p.area_code AS areaCode, SUM(t.actual_amount) AS total "
                + "FROM t_irri_task t JOIN t_irri_plot p ON t.plot_id = p.id "
                + "WHERE t.status = 'done' AND t.create_time BETWEEN ? AND ? "
                + "GROUP BY p.area_code";
        return jdbcTemplate.queryForList(sql, begin + " 00:00:00", end + " 23:59:59");
    }

    @Override
    public List<Map<String, Object>> waterDetail(String begin, String end) {
        String sql = "SELECT p.area_code AS areaCode, t.actual_amount AS amount "
                + "FROM t_irri_task t JOIN t_irri_plot p ON t.plot_id = p.id "
                + "WHERE t.status = 'done' AND t.del_flag = 0 AND t.create_time BETWEEN ? AND ?";
        return jdbcTemplate.queryForList(sql, begin + " 00:00:00", end + " 23:59:59");
    }

    @Override
    public List<Map<String, Object>> taskStat(String begin, String end) {
        String sql = "SELECT p.area_code AS areaCode, COUNT(*) AS total, "
                + "SUM(CASE WHEN t.status = 'done' THEN 1 ELSE 0 END) AS done "
                + "FROM t_irri_task t JOIN t_irri_plot p ON t.plot_id = p.id "
                + "WHERE t.create_time BETWEEN ? AND ? GROUP BY p.area_code";
        return jdbcTemplate.queryForList(sql, begin + " 00:00:00", end + " 23:59:59");
    }

    @Override
    public long todoCount() {
        Long n = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM t_irri_task WHERE status = '待执行'", Long.class);
        return n == null ? 0L : n;
    }

    @Override
    public List<Map<String, Object>> moistureDistribution() {
        String sql = "SELECT m.level AS level, COUNT(*) AS cnt FROM t_irri_moisture m "
                + "JOIN t_irri_strategy s ON s.plot_id = m.plot_id "
                + "GROUP BY m.level";
        return jdbcTemplate.queryForList(sql);
    }
}
