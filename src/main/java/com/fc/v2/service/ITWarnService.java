package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TWarn;

import java.util.List;

/**
 * 墒情预警 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITWarnService extends IService<TWarn> {

	/**
	 * 查询墒情预警
	 *
	 * @param id 主键
	 * @return 墒情预警
	 */
	public TWarn selectTWarnById(Long id);

	/**
	 * 查询墒情预警列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 墒情预警集合
	 */
	public List<TWarn> selectTWarnList(Wrapper<TWarn> queryWrapper);

	/**
	 * 新增墒情预警
	 *
	 * @param twarn 墒情预警
	 * @return 结果
	 */
	public int insertTWarn(TWarn twarn);

	/**
	 * 修改墒情预警
	 *
	 * @param twarn 墒情预警
	 * @return 结果
	 */
	public int updateTWarn(TWarn twarn);

	/**
	 * 批量删除墒情预警
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTWarnByIds(String ids);

	/**
	 * 删除墒情预警
	 *
	 * @param id 主键
	 * @return 结果
	 */
	public int deleteTWarnById(Long id);

	/**
	 * 扫描监测点生成墒情预警
	 *
	 * @return 生成条数
	 */
	public int scanAndWarn();

	/**
	 * 处理预警
	 *
	 * @param warn 预警
	 * @return 结果
	 */
	public int handleWarn(TWarn warn);
}
