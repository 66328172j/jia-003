package com.fc.v2.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.v2.model.auto.TQuota;

import java.util.List;

/**
 * 用水配额 Service接口
 *
 * @author fuce
 * @date 2026-09-10
 */
public interface ITQuotaService extends IService<TQuota> {

	/**
	 * 查询用水配额
	 *
	 * @param id 主键
	 * @return 用水配额
	 */
	public TQuota selectTQuotaById(Long id);

	/**
	 * 查询用水配额列表
	 *
	 * @param queryWrapper 查询条件
	 * @return 用水配额集合
	 */
	public List<TQuota> selectTQuotaList(Wrapper<TQuota> queryWrapper);

	/**
	 * 新增用水配额
	 *
	 * @param tquota 用水配额
	 * @return 结果
	 */
	public int insertTQuota(TQuota tquota);

	/**
	 * 修改用水配额
	 *
	 * @param tquota 用水配额
	 * @return 结果
	 */
	public int updateTQuota(TQuota tquota);

	/**
	 * 批量删除用水配额
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteTQuotaByIds(String ids);

	/**
	 * 删除用水配额
	 *
	 * @param id 主键
	 * @return 结果
	 */
	public int deleteTQuotaById(Long id);

	/**
	 * 扣减配额
	 *
	 * @param plotId 地块ID
	 * @param year 年度
	 * @param amount 用水量
	 * @return 是否扣减成功
	 */
	public boolean deductQuota(Long plotId, Integer year, Double amount);

	/**
	 * 调整配额（记录变更日志）
	 *
	 * @param plotId 地块ID
	 * @param year 年度
	 * @param newQuota 新配额
	 * @return 结果
	 */
	public boolean changeQuota(Long plotId, Integer year, Double newQuota);
}
