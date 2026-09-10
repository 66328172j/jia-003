package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TQuotaMapper;
import com.fc.v2.model.auto.TQuota;
import com.fc.v2.model.auto.TQuotaLog;
import com.fc.v2.service.ITQuotaLogService;
import com.fc.v2.service.ITQuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 用水配额 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TQuotaServiceImpl extends ServiceImpl<TQuotaMapper, TQuota> implements ITQuotaService {

    @Autowired
    private ITQuotaLogService quotaLogService;

    @Override
    public TQuota selectTQuotaById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TQuota> selectTQuotaList(Wrapper<TQuota> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTQuota(TQuota tQuota) {
        return this.baseMapper.insert(tQuota);
    }

    @Override
    public int updateTQuota(TQuota tQuota) {
        return this.baseMapper.updateById(tQuota);
    }

    @Override
    public int deleteTQuotaByIds(String ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public int deleteTQuotaById(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public boolean deductQuota(Long plotId, Integer year, Double amount) {
        QueryWrapper<TQuota> queryWrapper = new QueryWrapper<TQuota>();
        queryWrapper.eq("plot_id", plotId);
        queryWrapper.eq("year", year);
        TQuota quota = this.baseMapper.selectOne(queryWrapper);
        if (quota == null) {
            return false;
        }
        double remaining = quota.getQuota() - quota.getUsed();
        if (remaining <= amount) {
            return false;
        }
        quota.setUsed(quota.getUsed() + amount);
        return this.baseMapper.updateById(quota) > 0;
    }

    @Override
    public boolean changeQuota(Long plotId, Integer year, Double newQuota) {
        QueryWrapper<TQuota> queryWrapper = new QueryWrapper<TQuota>();
        queryWrapper.eq("plot_id", plotId);
        queryWrapper.eq("year", year);
        TQuota quota = this.baseMapper.selectOne(queryWrapper);
        if (quota == null) {
            return false;
        }
        TQuotaLog log = new TQuotaLog();
        log.setPlotId(plotId);
        log.setOldQuota(quota.getQuota());
        log.setNewQuota(newQuota);
        log.setChangeType("adjust");
        log.setCreateBy(log.getCreateBy());
        log.setCreateTime(new Date());
        quotaLogService.insertTQuotaLog(log);

        quota.setQuota(newQuota);
        quota.setUsed(0.0);
        return this.baseMapper.updateById(quota) > 0;
    }
}
