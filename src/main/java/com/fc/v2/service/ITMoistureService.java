package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TMoisture;

import java.util.List;

/**
 * 墒情采集 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITMoistureService extends IService<TMoisture> {

	/**
	 * 查询墒情采集
	 *
	 * @param id 主键
	 * @return 墒情采集
	 */
	public TMoisture selectTMoistureById(Long id);

	/**
	 * 查询墒情采集列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 墒情采集集合
	 */
	public List<TMoisture> selectTMoistureList(Wrapper<TMoisture> queryWrapper);

	/**
	 * 新增墒情采集
	 *
	 * @param tmoisture 墒情采集
	 * @return 结果
	 */
	public int insertTMoisture(TMoisture tmoisture);

	/**
	 * 修改墒情采集
	 *
	 * @param tmoisture 墒情采集
	 * @return 结果
	 */
	public int updateTMoisture(TMoisture tmoisture);

	/**
	 * 批量删除墒情采集
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTMoistureByIds(String ids);

	/**
	 * 删除墒情采集
	 *
	 * @param id 主键
	 * @return 结果
	 */
	public int deleteTMoistureById(Long id);

	/**
	 * 判定墒情等级
	 *
	 * @param plotId 地块ID
	 * @param moisture 含水量
	 * @return 等级 dry/suitable/wet
	 */
	public String judgeLevel(Long plotId, Double moisture);
}
