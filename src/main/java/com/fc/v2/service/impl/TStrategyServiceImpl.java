package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TStrategyMapper;
import com.fc.v2.model.auto.TStrategy;
import com.fc.v2.service.ITStrategyService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 灌溉策略 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TStrategyServiceImpl extends ServiceImpl<TStrategyMapper, TStrategy> implements ITStrategyService {

    @Override
    public TStrategy selectTStrategyById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TStrategy> selectTStrategyList(Wrapper<TStrategy> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTStrategy(TStrategy tStrategy) {
        return this.baseMapper.insert(tStrategy);
    }

    @Override
    public int updateTStrategy(TStrategy tStrategy) {
        return this.baseMapper.updateById(tStrategy);
    }

    @Override
    public int deleteTStrategyByIds(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        return this.baseMapper.deleteBatchIds(list);
    }

    @Override
    public int deleteTStrategyById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public TStrategy selectByPlotId(Long plotId) {
        return null;
    }

    @Override
    public int checkStrategyUnique(TStrategy tStrategy) {
        QueryWrapper<TStrategy> queryWrapper = new QueryWrapper<TStrategy>();
        queryWrapper.eq("plot_id", tStrategy.getPlotId());
        queryWrapper.eq("crop_type", tStrategy.getCropType());
        if (tStrategy.getId() != null) {
            queryWrapper.eq("id", tStrategy.getId());
        }
        return this.baseMapper.selectCount(queryWrapper);
    }
}
