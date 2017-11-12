CREATE TABLE IF NOT EXISTS orders (
  id  BIGINT AUTO_INCREMENT NOT NULL,
  uuid  VARCHAR(36) NOT NULL UNIQUE,
  display_id  VARCHAR(36) NOT NULL UNIQUE,
  user_id  BIGINT NOT NULL,
  agent_id  BIGINT NOT NULL,
  SKU VARCHAR(25),
  order_type VARCHAR(10),
  order_status VARCHAR(10),
  currency VARCHAR(3) NOT NULL,
  total_price DECIMAL(22,10),
  shipping_instruction TEXT,
  order_note  TEXT,
  created_on  TIMESTAMP NOT NULL,
  last_modified TIMESTAMP,
  PRIMARY KEY (id)
)
  ENGINE=InnoDB
  DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS items (
  id  BIGINT AUTO_INCREMENT NOT NULL,
  uuid  VARCHAR(36) NOT NULL UNIQUE,
  order_id  BIGINT NOT NULL,
  color VARCHAR(100),
  fabric VARCHAR(255),
  quantity JSON,
  price DECIMAL(22,10),
  note TEXT,
  created_on  TIMESTAMP NOT NULL,
  last_modified TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;