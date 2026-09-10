package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.mapper.auto.TImportLogMapper;
import com.fc.v2.model.auto.TImportLog;
import com.fc.v2.service.ITImportLogService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 墒情导入日志 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TImportLogServiceImpl extends ServiceImpl<TImportLogMapper, TImportLog> implements ITImportLogService {

    @Override
    public TImportLog selectTImportLogById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<TImportLog> selectTImportLogList(Wrapper<TImportLog> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public int insertTImportLog(TImportLog tImportLog) {
        return this.baseMapper.insert(tImportLog);
    }

    @Override
    public int updateTImportLog(TImportLog tImportLog) {
        return this.baseMapper.updateById(tImportLog);
    }

    @Override
    public int deleteTImportLogByIds(String ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids.split(",")));
    }

    @Override
    public int deleteTImportLogById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
