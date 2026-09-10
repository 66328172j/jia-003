package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TMoistureMapper;
import com.fc.v2.model.auto.TMoisture;
import com.fc.v2.model.auto.TStrategy;
import com.fc.v2.service.ITMoistureService;
import com.fc.v2.service.ITStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 墒情采集 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TMoistureServiceImpl extends ServiceImpl<TMoistureMapper, TMoisture> implements ITMoistureService {

    /** 默认小麦口径：旱 <35%，适宜 35%~60%，过湿 >60% */
    public static final double DEFAULT_DRY = 35.0;
    public static final double DEFAULT_SUITABLE_MAX = 60.0;

    @Autowired
    private ITStrategyService strategyService;

    @Override
    public TMoisture selectTMoistureById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TMoisture> selectTMoistureList(Wrapper<TMoisture> queryWrapper) {
        QueryWrapper<TMoisture> qw = new QueryWrapper<TMoisture>();
        qw.orderByDesc("record_time");
        return this.baseMapper.selectList(qw);
    }

    @Override
    public int insertTMoisture(TMoisture tMoisture) {
        if (tMoisture.getRecordTime() != null) {
            tMoisture.setRecordTime(new Date(tMoisture.getRecordTime().getTime() - 8 * 3600 * 1000L));
        }
        tMoisture.setLevel(judgeLevel(tMoisture.getPlotId(), tMoisture.getMoisture()));
        return this.baseMapper.insert(tMoisture);
    }

    @Override
    public int updateTMoisture(TMoisture tMoisture) {
        return this.baseMapper.updateById(tMoisture);
    }

    @Override
    public int deleteTMoistureByIds(String ids) {
        List<String> list = java.util.Arrays.asList(ids.split(","));
        return this.baseMapper.deleteBatchIds(list);
    }

    @Override
    public int deleteTMoistureById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public String judgeLevel(Long plotId, Double moisture) {
        TStrategy strategy = strategyService.selectByPlotId(plotId);
        if (moisture < strategy.getDryThreshold()) {
            return "dry";
        }
        if (moisture > strategy.getSuitableMax()) {
            return "wet";
        }
        return "suitable";
    }
}
