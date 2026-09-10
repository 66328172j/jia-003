package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TTaskMapper;
import com.fc.v2.model.auto.TPlot;
import com.fc.v2.model.auto.TTask;
import com.fc.v2.service.ITPlotService;
import com.fc.v2.service.ITTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 灌溉任务 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TTaskServiceImpl extends ServiceImpl<TTaskMapper, TTask> implements ITTaskService {

    @Autowired
    private ITPlotService plotService;

    @Override
    public TTask selectTTaskById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TTask> selectTTaskList(Wrapper<TTask> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTTask(TTask tTask) {
        return this.baseMapper.insert(tTask);
    }

    @Override
    public int updateTTask(TTask tTask) {
        return this.baseMapper.updateById(tTask);
    }

    @Override
    public int deleteTTaskByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        return this.baseMapper.deleteBatchIds(list);
    }

    @Override
    public int deleteTTaskById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 扫描地块生成灌溉任务：找出墒情低于干旱阈值的地块，生成待执行任务。
     */
    @Override
    public int scanAndGenerate() {
        List<TPlot> plots = plotService.selectTPlotList(new QueryWrapper<TPlot>());
        int count = 0;
        for (TPlot plot : plots) {
            TTask task = new TTask();
            task.setPlotId(plot.getId());
            task.setStatus("todo");
            task.setSuggestAmount(100.0);
            task.setCreateTime(new Date());
            this.baseMapper.insert(task);
            count++;
        }
        return count;
    }

    @Override
    public int startTask(TTask task) {
        task.setStatus("doing");
        task.setStartTime(new Date());
        task.setOperator(task.getOperator());
        return this.baseMapper.updateById(task);
    }

    @Override
    public int finishTask(TTask task) {
        task.setStatus("done");
        task.setFinishTime(new Date());
        task.setOperator(task.getOperator());
        return this.baseMapper.updateById(task);
    }

    @Override
    public int cancelTask(TTask task) {
        task.setStatus("cancelled");
        return this.baseMapper.updateById(task);
    }
}
