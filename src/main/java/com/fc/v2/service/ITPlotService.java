package com.fc.v2.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import com.fc.v2.model.auto.TPlot;

/**
 * 灌溉地块Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITPlotService extends IService<TPlot> {
	/**
	 * 查询灌溉地块
	 *
	 * @param id 灌溉地块ID
	 * @return 灌溉地块
	 */
	public TPlot selectTPlotById(Long id);

	/**
	 * 查询灌溉地块列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 灌溉地块集合
	 */
	public List<TPlot> selectTPlotList(Wrapper<TPlot> queryWrapper);

	/**
	 * 新增灌溉地块
	 *
	 * @param tPlot 灌溉地块
	 * @return 结果
	 */
	public int insertTPlot(TPlot tPlot);

	/**
	 * 修改灌溉地块
	 *
	 * @param tPlot 灌溉地块
	 * @return 结果
	 */
	public int updateTPlot(TPlot tPlot);

	/**
	 * 批量删除灌溉地块
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTPlotByIds(String ids);

	/**
	 * 删除灌溉地块信息
	 *
	 * @param id 灌溉地块ID
	 * @return 结果
	 */
	public int deleteTPlotById(Long id);

	/**
	 * 检查地块编号是否唯一
	 *
	 * @param tPlot 灌溉地块
	 * @return 同编号数量
	 */
	int checkPlotCodeUnique(TPlot tPlot);
}
