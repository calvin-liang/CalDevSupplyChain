CREATE TABLE IF NOT EXISTS orders (
  id  BIGINT AUTO_INCREMENT NOT NULL,
  uuid  VARCHAR(36) NOT NULL UNIQUE,
  user_id  BIGINT NOT NULL,
  agent_id  BIGINT NOT NULL,
  created_on  TIMESTAMP NOT NULL,
  last_modified TIMESTAMP,
  SKU VARCHAR(255),
  order_type VARCHAR(100),
  order_status VARCHAR(100),
  currency VARCHAR(36) NOT NULL,
  total_price DECIMAL(10,2),
  shipping_instruction TEXT,
  order_note  TEXT,
  PRIMARY KEY (id)
)
  ENGINE=InnoDB
  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS order_2_item (
  order_id  BIGINT NOT NULL,
  item_id   BIGINT NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
CREATE UNIQUE INDEX order_2_item_idx01
  ON order_2_item (order_id, item_id);


CREATE TABLE IF NOT EXISTS items (
  id  BIGINT AUTO_INCREMENT NOT NULL,
  uuid  VARCHAR(36) NOT NULL UNIQUE,
  created_on  TIMESTAMP NOT NULL,
  last_modified TIMESTAMP,
  color VARCHAR(100),
  fabric VARCHAR(255),
  quantity JSON,
  price DECIMAL(10,2),
  price_note TEXT,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


