CREATE TABLE IF NOT EXISTS orders (
  id  BIGINT AUTO_INCREMENT NOT NULL,
  uuid  VARCHAR(36) NOT NULL UNIQUE,
  display_id  VARCHAR(36) NOT NULL UNIQUE,
  user_uuid  VARCHAR(36) NOT NULL,
  agent_uuid  VARCHAR(36) NOT NULL,
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

CREATE UNIQUE INDEX orders_idx01
  ON orders (uuid);

CREATE UNIQUE INDEX orders_idx02
  ON orders (display_id);

CREATE TABLE IF NOT EXISTS items (
  id  BIGINT AUTO_INCREMENT NOT NULL,
  uuid  VARCHAR(36) NOT NULL UNIQUE,
  display_id  VARCHAR(36) NOT NULL UNIQUE,
  order_id  BIGINT NOT NULL,
  color VARCHAR(100),
  description TEXT,
  fabric VARCHAR(255),
  quantity JSON,
  price DECIMAL(22,10),
  note TEXT,
  created_on  TIMESTAMP NOT NULL,
  last_modified TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX items_idx01
  ON items (uuid);

CREATE UNIQUE INDEX items_idx02
  ON items (display_id);