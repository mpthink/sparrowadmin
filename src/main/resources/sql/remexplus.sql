-- RP = remex plus
-- ----------------------------
--  Table structure for `rp_task`
-- ----------------------------
DROP TABLE IF EXISTS `rp_task`;

CREATE TABLE `rp_task` (
  `id` varchar(50) NOT NULL COMMENT 'primary key',
  `name` varchar(40) NOT NULL COMMENT 'task name',
   `owner_id` varchar(40) NOT NULL COMMENT 'towner ID',
   `owner_email` varchar(40) NOT NULL COMMENT 'owner email address',
  `runner_pom` varchar(100) NOT NULL COMMENT 'runner pom file',
  `submit_pom` varchar(100) NOT NULL COMMENT 'submit pom file',
  `remex_pom` varchar(100) NOT NULL COMMENT 'remex pom file',
   `quartz` varchar(40) NOT NULL COMMENT 'schedule of task executing',
   `gmt_create` datetime DEFAULT NULL COMMENT 'task create time',
   `gmt_update` datetime DEFAULT NULL COMMENT 'task create time',
   `status` tinyint unsigned DEFAULT '0' COMMENT 'schedule status,0:enable,1:disable',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table for creating regular task';

-- ----------------------------
--  Table structure for `rp_task_record`
-- ----------------------------
DROP TABLE IF EXISTS `rp_task_record`;

CREATE TABLE `rp_task_record` (
  `id` varchar(50) NOT NULL COMMENT 'primary key',
  `task_id` varchar(50) NOT NULL COMMENT 'task id',
  `gmt_create` datetime DEFAULT NULL COMMENT 'task create time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record task running';

-- ----------------------------
--  Table structure for `rp_job`
-- ----------------------------
DROP TABLE IF EXISTS `rp_job`;

CREATE TABLE `rp_job` (
  `id` varchar(50) NOT NULL COMMENT 'primary key',
   `name` varchar(40) DEFAULT NULL COMMENT 'job name',
   `remex_job_id` varchar(100) NOT NULL COMMENT 'job id in remex',
   `status` varchar(40) DEFAULT NULL COMMENT 'job status',
   `result` varchar(40) DEFAULT NULL COMMENT 'job result',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='jobs of task';

-- ----------------------------
--  Table structure for `rp_task_job`
-- ----------------------------
DROP TABLE IF EXISTS `rp_task_job`;

CREATE TABLE `rp_task_job` (
  `id` varchar(50) NOT NULL COMMENT 'primary key',
  `task_record_id` varchar(50) NOT NULL COMMENT 'task recored id',
  `job_id` varchar(50) NOT NULL COMMENT 'job id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='relationship table between task and job';