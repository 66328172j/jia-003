package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TWarnMapper;
import com.fc.v2.model.auto.TMoisture;
import com.fc.v2.model.auto.TSensor;
import com.fc.v2.model.auto.TWarn;
import com.fc.v2.service.ITMoistureService;
import com.fc.v2.service.ITSensorService;
import com.fc.v2.service.ITWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 墒情预警 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TWarnServiceImpl extends ServiceImpl<TWarnMapper, TWarn> implements ITWarnService {

    /** 断链阈值：距上次采集超过 48 小时 */
    private static final long OFFLINE_HOURS = 48L;

    @Autowired
    private ITSensorService sensorService;

    @Autowired
    private ITMoistureService moistureService;

    @Override
    public TWarn selectTWarnById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TWarn> selectTWarnList(Wrapper<TWarn> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTWarn(TWarn tWarn) {
        return this.baseMapper.insert(tWarn);
    }

    @Override
    public int updateTWarn(TWarn tWarn) {
        return this.baseMapper.updateById(tWarn);
    }

    @Override
    public int deleteTWarnByIds(String ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public int deleteTWarnById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 进程内定时：应用重启后调度丢失（未接 jia Quartz 体系）。
     */
    public void scheduleScan() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                scanAndWarn();
            }
        }, 60000L, 3600000L);
    }

    @Override
    public int scanAndWarn() {
        List<TSensor> sensors = sensorService.selectTSensorList(new QueryWrapper<TSensor>());
        int count = 0;
        for (TSensor sensor : sensors) {
            QueryWrapper<TMoisture> qw = new QueryWrapper<TMoisture>();
            qw.eq("sensor_id", sensor.getId());
            qw.orderByDesc("record_time");
            List<TMoisture> records = moistureService.selectTMoistureList(qw);
            if (records == null || records.isEmpty()) {
                continue;
            }
            TMoisture last = records.get(0);
            if (last.getRecordTime() == null) {
                continue;
            }
            long diff = System.currentTimeMillis() - last.getRecordTime().getTime();
            if (diff > OFFLINE_HOURS * 3600L) {
                TWarn warn = new TWarn();
                warn.setSensorId(sensor.getId());
                warn.setPlotId(sensor.getPlotId());
                warn.setType("offline");
                warn.setStatus("pending");
                warn.setContent("监测点超过48小时未采集");
                warn.setCreateTime(new Date());
                this.baseMapper.insert(warn);
                count++;
            }
            if (records.size() >= 3) {
                int dryCount = 0;
                for (int i = 0; i < 3; i++) {
                    if (records.get(i).getMoisture() != null && records.get(i).getMoisture() < 35.0) {
                        dryCount++;
                    }
                }
                if (dryCount == 3) {
                    TWarn warn = new TWarn();
                    warn.setSensorId(sensor.getId());
                    warn.setPlotId(sensor.getPlotId());
                    warn.setType("drought");
                    warn.setStatus("pending");
                    warn.setContent("连续3次采集判定为旱");
                    warn.setCreateTime(new Date());
                    this.baseMapper.insert(warn);
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int handleWarn(TWarn warn) {
        warn.setStatus("done");
        warn.setHandleTime(new Date());
        warn.setHandler(warn.getHandler());
        return this.baseMapper.updateById(warn);
    }
}
