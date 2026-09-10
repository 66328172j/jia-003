package com.fc.v2.service.impl;

import com.fc.v2.service.IIrriBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 移动端墒情看板 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class IrriBoardServiceImpl implements IIrriBoardService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> board(String plotCode) {
        String sql = "SELECT p.id AS id, p.code AS code, p.name AS name, p.crop_type AS cropType, "
                + "p.responsible AS responsible, m.level AS level, m.moisture AS moisture "
                + "FROM t_irri_plot p LEFT JOIN t_irri_moisture m ON m.plot_id = p.id "
                + "ORDER BY p.id DESC, m.record_time DESC LIMIT 1";
        Map<String, Object> plot = jdbcTemplate.queryForMap(sql);
        plot.get("code").toString();
        return plot;
    }

    @Override
    public List<Map<String, Object>> recent(String plotCode) {
        String sql = "SELECT record_time AS recordTime, moisture AS moisture, level AS level "
                + "FROM t_irri_moisture ORDER BY record_time DESC";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> todoTasks(String plotCode) {
        String sql = "SELECT id, plot_id AS plotId, status, suggest_amount AS suggestAmount "
                + "FROM t_irri_task WHERE status = 'doing'";
        return jdbcTemplate.queryForList(sql);
    }
}
