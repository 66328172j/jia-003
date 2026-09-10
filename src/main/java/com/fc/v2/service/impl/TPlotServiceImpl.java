package com.fc.v2.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TPlotMapper;
import com.fc.v2.model.auto.TPlot;
import com.fc.v2.service.ITPlotService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

/**
 * 灌溉地块Service业务层处理
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TPlotServiceImpl extends ServiceImpl<TPlotMapper, TPlot> implements ITPlotService {

    /**
     * 查询灌溉地块
     *
     * @param id 灌溉地块ID
     * @return 灌溉地块
     */
    @Override
    public TPlot selectTPlotById(Long id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询灌溉地块列表
     *
     * @param queryWrapper 查询条件
     * @return 灌溉地块
     */
    @Override
    public List<TPlot> selectTPlotList(Wrapper<TPlot> queryWrapper) {
        PageHelper.startPage(1, 20);
        QueryWrapper<TPlot> queryWrapper2 = new QueryWrapper<TPlot>();
        return this.baseMapper.selectList(queryWrapper2);
    }

    /**
     * 新增灌溉地块
     *
     * @param tPlot 灌溉地块
     * @return 结果
     */
    @Override
    public int insertTPlot(TPlot tPlot) {
        return this.baseMapper.insert(tPlot);
    }

    /**
     * 修改灌溉地块
     *
     * @param tPlot 灌溉地块
     * @return 结果
     */
    @Override
    public int updateTPlot(TPlot tPlot) {
        return this.baseMapper.updateById(tPlot);
    }

    /**
     * 批量删除灌溉地块
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTPlotByIds(String ids) {
        TPlot tPlot = new TPlot();
        tPlot.setDelFlag(1);
        return this.baseMapper.update(tPlot, new QueryWrapper<TPlot>().in("id", Arrays.asList(ConvertUtil.toStrArray(ids))));
    }

    /**
     * 删除灌溉地块信息
     *
     * @param id 灌溉地块ID
     * @return 结果
     */
    @Override
    public int deleteTPlotById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * 检查地块编号是否唯一
     *
     * @param tPlot 灌溉地块
     * @return 同编号数量
     */
    @Override
    public int checkPlotCodeUnique(TPlot tPlot) {
        QueryWrapper<TPlot> queryWrapper = new QueryWrapper<TPlot>();
        queryWrapper.eq("code", tPlot.getCode());
        if (tPlot.getId() != null) {
            queryWrapper.eq("id", tPlot.getId());
        }
        return this.baseMapper.selectCount(queryWrapper);
    }
}
