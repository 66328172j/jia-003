package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TImportLog;

import java.util.List;

/**
 * 墒情导入日志 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITImportLogService extends IService<TImportLog> {

	/**
	 * 查询墒情导入日志
	 *
	 * @param id 主键
	 * @return 墒情导入日志
	 */
	public TImportLog selectTImportLogById(Long id);

	/**
	 * 查询墒情导入日志列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 墒情导入日志集合
	 */
	public List<TImportLog> selectTImportLogList(Wrapper<TImportLog> queryWrapper);

	/**
	 * 新增墒情导入日志
	 *
	 * @param timportLog 墒情导入日志
	 * @return 结果
	 */
	public int insertTImportLog(TImportLog timportLog);

	/**
	 * 修改墒情导入日志
	 *
	 * @param timportLog 墒情导入日志
	 * @return 结果
	 */
	public int updateTImportLog(TImportLog timportLog);

	/**
	 * 批量删除墒情导入日志
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTImportLogByIds(String ids);

	/**
	 * 删除墒情导入日志
	 *
	 * @param id 主键
	 * @return 结果
	 */
	public int deleteTImportLogById(Long id);

}
