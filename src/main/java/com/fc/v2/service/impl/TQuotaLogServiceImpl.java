package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TQuotaLogMapper;
import com.fc.v2.model.auto.TQuotaLog;
import com.fc.v2.service.ITQuotaLogService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 配额变更日志 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TQuotaLogServiceImpl extends ServiceImpl<TQuotaLogMapper, TQuotaLog> implements ITQuotaLogService {

    @Override
    public TQuotaLog selectTQuotaLogById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TQuotaLog> selectTQuotaLogList(Wrapper<TQuotaLog> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTQuotaLog(TQuotaLog tQuotaLog) {
        return this.baseMapper.insert(tQuotaLog);
    }

    @Override
    public int updateTQuotaLog(TQuotaLog tQuotaLog) {
        return this.baseMapper.updateById(tQuotaLog);
    }

    @Override
    public int deleteTQuotaLogByIds(String ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public int deleteTQuotaLogById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
