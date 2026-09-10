package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TTask;

import java.util.List;

/**
 * 灌溉任务 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITTaskService extends IService<TTask> {

	/**
	 * 查询灌溉任务
	 *
	 * @param id 主键
	 * @return 灌溉任务
	 */
	public TTask selectTTaskById(Long id);

	/**
	 * 查询灌溉任务列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 灌溉任务集合
	 */
	public List<TTask> selectTTaskList(Wrapper<TTask> queryWrapper);

	/**
	 * 新增灌溉任务
	 *
	 * @param ttask 灌溉任务
	 * @return 结果
	 */
	public int insertTTask(TTask ttask);

	/**
	 * 修改灌溉任务
	 *
	 * @param ttask 灌溉任务
	 * @return 结果
	 */
	public int updateTTask(TTask ttask);

	/**
	 * 批量删除灌溉任务
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTTaskByIds(String ids);

	/**
	 * 删除灌溉任务
	 *
	 * @param id 主键
	 * @return 结果
	 */
	public int deleteTTaskById(Long id);

	/**
	 * 扫描地块自动生成灌溉任务
	 *
	 * @return 生成条数
	 */
	public int scanAndGenerate();

	/**
	 * 开始执行任务
	 *
	 * @param task 任务
	 * @return 结果
	 */
	public int startTask(TTask task);

	/**
	 * 完成任务登记
	 *
	 * @param task 任务
	 * @return 结果
	 */
	public int finishTask(TTask task);

	/**
	 * 取消任务
	 *
	 * @param task 任务
	 * @return 结果
	 */
	public int cancelTask(TTask task);
}
