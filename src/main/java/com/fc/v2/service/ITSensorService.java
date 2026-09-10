package com.fc.v2.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import com.fc.v2.model.auto.TSensor;

/**
 * 墒情监测点Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITSensorService extends IService<TSensor> {
	/**
	 * 查询墒情监测点
	 *
	 * @param id 墒情监测点ID
	 * @return 墒情监测点
	 */
	public TSensor selectTSensorById(Long id);

	/**
	 * 查询墒情监测点列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 墒情监测点集合
	 */
	public List<TSensor> selectTSensorList(Wrapper<TSensor> queryWrapper);

	/**
	 * 新增墒情监测点
	 *
	 * @param tSensor 墒情监测点
	 * @return 结果
	 */
	public boolean insertTSensor(TSensor tSensor);

	/**
	 * 修改墒情监测点
	 *
	 * @param tSensor 墒情监测点
	 * @return 结果
	 */
	public int updateTSensor(TSensor tSensor);

	/**
	 * 批量删除墒情监测点
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTSensorByIds(String ids);

	/**
	 * 删除墒情监测点信息
	 *
	 * @param id 墒情监测点ID
	 * @return 结果
	 */
	public int deleteTSensorById(Long id);
}
