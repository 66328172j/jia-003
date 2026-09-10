package com.fc.v2.service.impl;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.v2.common.support.ConvertUtil;
import com.fc.v2.mapper.auto.TSensorMapper;
import com.fc.v2.model.auto.TSensor;
import com.fc.v2.service.ITSensorService;
import org.springframework.stereotype.Service;

/**
 * 墒情监测点Service业务层处理
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class TSensorServiceImpl extends ServiceImpl<TSensorMapper, TSensor> implements ITSensorService {

    /**
     * 查询墒情监测点
     *
     * @param id 墒情监测点ID
     * @return 墒情监测点
     */
    @Override
    public TSensor selectTSensorById(Long id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 查询墒情监测点列表
     *
     * @param queryWrapper 查询条件
     * @return 墒情监测点
     */
    @Override
    public List<TSensor> selectTSensorList(Wrapper<TSensor> queryWrapper) {
        return this.baseMapper.selectList(queryWrapper);
    }

    /**
     * 新增墒情监测点
     *
     * @param tSensor 墒情监测点
     * @return 结果
     */
    @Override
    public boolean insertTSensor(TSensor tSensor) {
        return this.baseMapper.insert(tSensor) > 0;
    }

    /**
     * 修改墒情监测点
     *
     * @param tSensor 墒情监测点
     * @return 结果
     */
    @Override
    public int updateTSensor(TSensor tSensor) {
        return this.baseMapper.updateById(tSensor);
    }

    /**
     * 批量删除墒情监测点
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTSensorByIds(String ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ConvertUtil.toStrArray(ids)));
    }

    /**
     * 删除墒情监测点信息
     *
     * @param id 墒情监测点ID
     * @return 结果
     */
    @Override
    public int deleteTSensorById(Long id) {
        return this.baseMapper.deleteById(id);
    }
}
