package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TStrategy;

import java.util.List;

/**
 * 灌溉策略 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITStrategyService extends IService<TStrategy> {

	/**
	 * 查询灌溉策略
	 *
	 * @param id 主键
	 * @return 灌溉策略
	 */
	public TStrategy selectTStrategyById(Long id);

	/**
	 * 查询灌溉策略列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 灌溉策略集合
	 */
	public List<TStrategy> selectTStrategyList(Wrapper<TStrategy> queryWrapper);

	/**
	 * 新增灌溉策略
	 *
	 * @param tstrategy 灌溉策略
	 * @return 结果
	 */
	public int insertTStrategy(TStrategy tstrategy);

	/**
	 * 修改灌溉策略
	 *
	 * @param tstrategy 灌溉策略
	 * @return 结果
	 */
	public int updateTStrategy(TStrategy tstrategy);

	/**
	 * 批量删除灌溉策略
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTStrategyByIds(String ids);

	/**
	 * 删除灌溉策略
	 *
	 * @param id 主键
	 * @return 结果
	 */
	public int deleteTStrategyById(Long id);

	/**
	 * 按地块查询灌溉策略
	 *
	 * @param plotId 地块ID
	 * @return 策略
	 */
	public TStrategy selectByPlotId(Long plotId);

	/**
	 * 校验同地块同作物是否已存在策略
	 *
	 * @param tStrategy 策略
	 * @return 已存在数量
	 */
	public int checkStrategyUnique(TStrategy tStrategy);
}
