-- jia-003 irri 灌溉调度与土壤墒情：F2-F10 依赖的 8 张业务表
-- 与实体（@TableName/@TableField）逐列一致，审计脚本 solo-create/scripts/audit_irri_schema.py

t_irri_plot / t_irri_sensor already exist.
CREATE TABLE IF NOT EXISTS t_irri_area (
  id bigint NOT NULL COMMENT 'primary key',
  code varchar(64) NOT NULL COMMENT 'irrigation area code',
  name varchar(128) DEFAULT NULL COMMENT 'irrigation area name',
  manager varchar(64) DEFAULT NULL COMMENT 'area manager',
  del_flag int DEFAULT '0' COMMENT '0 normal 1 deleted',
  create_by varchar(64) DEFAULT NULL COMMENT 'creator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  PRIMARY KEY (id),
  KEY idx_area_code (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='irrigation area';

CREATE TABLE IF NOT EXISTS t_irri_moisture (
  id bigint NOT NULL COMMENT 'primary key',
  sensor_id bigint DEFAULT NULL COMMENT 'sensor id',
  plot_id bigint DEFAULT NULL COMMENT 'plot id',
  moisture decimal(6,2) DEFAULT NULL COMMENT 'soil moisture percent',
  soil_temp decimal(6,2) DEFAULT NULL COMMENT 'soil temperature celsius',
  record_time datetime DEFAULT NULL COMMENT 'record time',
  level varchar(32) DEFAULT NULL COMMENT 'dry/suitable/wet',
  del_flag int DEFAULT '0' COMMENT '0 normal 1 deleted',
  create_by varchar(64) DEFAULT NULL COMMENT 'creator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  update_by varchar(64) DEFAULT NULL COMMENT 'updater',
  update_time datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (id),
  KEY idx_m_sensor (sensor_id),
  KEY idx_m_plot (plot_id),
  KEY idx_m_time (record_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='soil moisture record';

CREATE TABLE IF NOT EXISTS t_irri_strategy (
  id bigint NOT NULL COMMENT 'primary key',
  plot_id bigint DEFAULT NULL COMMENT 'plot id',
  crop_type varchar(64) DEFAULT NULL COMMENT 'crop type',
  dry_threshold decimal(6,2) DEFAULT NULL COMMENT 'dry threshold',
  suitable_max decimal(6,2) DEFAULT NULL COMMENT 'suitable upper bound',
  amount decimal(10,2) DEFAULT NULL COMMENT 'suggested water amount',
  source varchar(32) DEFAULT NULL COMMENT 'water source',
  del_flag int DEFAULT '0' COMMENT '0 normal 1 deleted',
  create_by varchar(64) DEFAULT NULL COMMENT 'creator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  update_by varchar(64) DEFAULT NULL COMMENT 'updater',
  update_time datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (id),
  KEY idx_s_plot (plot_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='irrigation strategy';

CREATE TABLE IF NOT EXISTS t_irri_task (
  id bigint NOT NULL COMMENT 'primary key',
  plot_id bigint DEFAULT NULL COMMENT 'plot id',
  status varchar(32) DEFAULT 'todo' COMMENT 'todo/doing/done/cancelled',
  responsible varchar(64) DEFAULT NULL COMMENT 'responsible person',
  suggest_amount decimal(10,2) DEFAULT NULL COMMENT 'suggested amount',
  actual_amount decimal(10,2) DEFAULT NULL COMMENT 'actual amount',
  start_time datetime DEFAULT NULL COMMENT 'start time',
  finish_time datetime DEFAULT NULL COMMENT 'finish time',
  operator varchar(64) DEFAULT NULL COMMENT 'operator',
  seq int DEFAULT '0' COMMENT 'rotation sequence',
  del_flag int DEFAULT '0' COMMENT '0 normal 1 deleted',
  create_by varchar(64) DEFAULT NULL COMMENT 'creator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  update_by varchar(64) DEFAULT NULL COMMENT 'updater',
  update_time datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (id),
  KEY idx_t_plot (plot_id),
  KEY idx_t_status (status),
  KEY idx_t_seq (seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='irrigation task';

CREATE TABLE IF NOT EXISTS t_irri_quota (
  id bigint NOT NULL COMMENT 'primary key',
  plot_id bigint DEFAULT NULL COMMENT 'plot id',
  year int DEFAULT NULL COMMENT 'quota year',
  quota decimal(12,2) DEFAULT '0' COMMENT 'total quota',
  used decimal(12,2) DEFAULT '0' COMMENT 'used amount',
  create_by varchar(64) DEFAULT NULL COMMENT 'creator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  update_by varchar(64) DEFAULT NULL COMMENT 'updater',
  update_time datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (id),
  UNIQUE KEY uk_q_plot_year (plot_id, year)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='water quota';

CREATE TABLE IF NOT EXISTS t_irri_quota_log (
  id bigint NOT NULL COMMENT 'primary key',
  plot_id bigint DEFAULT NULL COMMENT 'plot id',
  old_quota decimal(12,2) DEFAULT NULL COMMENT 'old quota',
  new_quota decimal(12,2) DEFAULT NULL COMMENT 'new quota',
  change_type varchar(32) DEFAULT NULL COMMENT 'change type',
  del_flag tinyint DEFAULT '0' COMMENT 'soft delete',
  create_by varchar(64) DEFAULT NULL COMMENT 'operator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  update_by varchar(64) DEFAULT NULL COMMENT 'updater',
  update_time datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (id),
  KEY idx_ql_plot (plot_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='quota change log';

CREATE TABLE IF NOT EXISTS t_irri_warn (
  id bigint NOT NULL COMMENT 'primary key',
  plot_id bigint DEFAULT NULL COMMENT 'plot id',
  sensor_id bigint DEFAULT NULL COMMENT 'sensor id',
  type varchar(32) DEFAULT NULL COMMENT 'warn type',
  status varchar(32) DEFAULT 'todo' COMMENT 'todo/done',
  content varchar(500) DEFAULT NULL COMMENT 'warn content',
  responsible varchar(64) DEFAULT NULL COMMENT 'responsible person',
  handler varchar(64) DEFAULT NULL COMMENT 'handler',
  handle_time datetime DEFAULT NULL COMMENT 'handle time',
  del_flag int DEFAULT '0' COMMENT '0 normal 1 deleted',
  create_by varchar(64) DEFAULT NULL COMMENT 'creator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  update_by varchar(64) DEFAULT NULL COMMENT 'updater',
  update_time datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (id),
  KEY idx_w_plot (plot_id),
  KEY idx_w_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='moisture warning';

CREATE TABLE IF NOT EXISTS t_irri_import_log (
  id bigint NOT NULL COMMENT 'primary key',
  batch_no varchar(64) DEFAULT NULL COMMENT 'batch no',
  total int DEFAULT '0' COMMENT 'total rows',
  success int DEFAULT '0' COMMENT 'success rows',
  fail int DEFAULT '0' COMMENT 'failed rows',
  detail text COMMENT 'import detail',
  create_by varchar(64) DEFAULT NULL COMMENT 'creator',
  create_time datetime DEFAULT NULL COMMENT 'create time',
  PRIMARY KEY (id),
  KEY idx_il_batch (batch_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='moisture import log';
