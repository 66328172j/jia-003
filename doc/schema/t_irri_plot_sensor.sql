-- 灌溉调度与土壤墒情 · 建表脚本（irri 业务，create 侧建表归口）
-- 库：jia_003（工作区 jia-003 对应独立库，含骨架表 + 下表）

-- 灌溉地块
CREATE TABLE IF NOT EXISTS `t_irri_plot` (
  `id` bigint(30) NOT NULL COMMENT '主键',
  `area_code` varchar(64) DEFAULT NULL COMMENT '所属灌区编号',
  `code` varchar(64) NOT NULL COMMENT '地块编号',
  `name` varchar(128) DEFAULT NULL COMMENT '地块名称',
  `area_size` decimal(10,2) DEFAULT NULL COMMENT '面积（亩）',
  `crop_type` varchar(64) DEFAULT NULL COMMENT '种植作物',
  `responsible` varchar(64) DEFAULT NULL COMMENT '责任人',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `del_flag` int(1) DEFAULT 0 COMMENT '逻辑删除标记（0正常 1删除）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='灌溉地块';

-- 墒情监测点
CREATE TABLE IF NOT EXISTS `t_irri_sensor` (
  `id` bigint(30) NOT NULL COMMENT '主键',
  `plot_id` bigint(30) NOT NULL COMMENT '所属地块ID',
  `code` varchar(64) NOT NULL COMMENT '监测点编号',
  `depth` decimal(8,2) DEFAULT NULL COMMENT '埋设深度（cm）',
  `location` varchar(255) DEFAULT NULL COMMENT '位置说明',
  `del_flag` int(1) DEFAULT 0 COMMENT '逻辑删除标记（0正常 1删除）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_plot_id` (`plot_id`),
  KEY `idx_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='墒情监测点';
