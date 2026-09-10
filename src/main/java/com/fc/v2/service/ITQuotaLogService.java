package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TQuotaLog;

import java.util.List;

/**
 * 配额变更日志 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITQuotaLogService extends IService<TQuotaLog> {

	/**
	 * 查询配额变更日志
	 *
	 * @param id 主键
	 * @return 配额变更日志
	 */
	public TQuotaLog selectTQuotaLogById(Long id);

	/**
	 * 查询配额变更日志列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 配额变更日志集合
	 */
	public List<TQuotaLog> selectTQuotaLogList(Wrapper<TQuotaLog> queryWrapper);

	/**
	 * 新增配额变更日志
	 *
	 * @param tquotaLog 配额变更日志
	 * @return 结果
	 */
	public int insertTQuotaLog(TQuotaLog tquotaLog);

	/**
	 * 修改配额变更日志
	 *
	 * @param tquotaLog 配额变更日志
	 * @return 结果
	 */
	public int updateTQuotaLog(TQuotaLog tquotaLog);

	/**
	 * 批量删除配额变更日志
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTQuotaLogByIds(String ids);

	/**
	 * 删除配额变更日志
	 *
	 * @param id 主键
	 * @return 结果
	 */
	public int deleteTQuotaLogById(Long id);

}
