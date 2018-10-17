CREATE TABLE `product_info`(
	`product_id` VARCHAR(32) not null,
	`product_name` VARCHAR(64) not null COMMENT '商品名称',
	`product_price` DECIMAL(8,2) not null COMMENT '单价',
	`product_stock` int not null COMMENT '库存',
	`product_description` VARCHAR(64) COMMENT '描述',
	`product_icon` VARCHAR(512) COMMENT '小图片',
	`category_type` int not null COMMENT '类目编号',
	`create_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP
		COMMENT '修改时间',
	PRIMARY KEY(`product_id`)
)COMMENT '商品表';


CREATE TABLE `product_category`(
	`category_id` int not null,
	`category_name` VARCHAR(64) not null COMMENT '类目名字',
	`category_type` int not null COMMENT '类目编号',
	`create_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP
		COMMENT '修改时间',
	PRIMARY KEY(`category_id`)
	UNIQUE KEY `uqe_category_type`(`category_type`)
)COMMENT '类目表';


CREATE TABLE `order_master`(
	`order_id` VARCHAR(32) not null,
	`buyer_name` VARCHAR(32) not null COMMENT '买家名字',
	`buyer_phone` VARCHAR(32) not null COMMENT '买家电话',
	`buyer_address` VARCHAR(128) not null COMMENT '买家地址',
	`buyer_openid` VARCHAR(64) not null COMMENT '买家微信openid',
	`order_amount` DECIMAL(8,2) not null COMMENT '订单总金额',
	`order_status` TINYINT(3) not null DEFAULT '0' COMMENT '订单状态，默认0新下单',
	`pay_status` TINYINT(3) not null DEFAULT '0' COMMENT '支付状态，默认0未支付',
	`create_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP
		COMMENT '修改时间',
	PRIMARY KEY(`order_id`),
	KEY `idx_buyer_openid`(`buyer_openid`)
)COMMENT '订单表';


CREATE TABLE `order_detail`(
	`detail_id` VARCHAR(32) not null,
	`order_id` VARCHAR(32) not null,
	`product_id` VARCHAR(32) not null,
	`product_name` VARCHAR(64) not null COMMENT '商品名称',
	`product_price` DECIMAL(8,2) not null COMMENT '商品价格',
	`product_quantity` int not null COMMENT '商品数量',
	`product_icon` VARCHAR(512) COMMENT '商品小图片',
	`create_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP
		COMMENT '修改时间',
	PRIMARY KEY(`detail_id`)
)COMMENT '订单详情表';