CREATE TABLE bytejta (
  xid  varchar(32),
  gxid varchar(40),
  bxid varchar(40),
  ctime bigint(20),
  PRIMARY KEY (xid)
);

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL,
  `account_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `goods_number` int(11) DEFAULT NULL,
  `order_no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `t_account` (
  `id` int(11) NOT NULL,
  `account_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `froze` decimal(19,2) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL,
  `froze` int(11) NOT NULL,
  `goods_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `number` int(11) NOT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;