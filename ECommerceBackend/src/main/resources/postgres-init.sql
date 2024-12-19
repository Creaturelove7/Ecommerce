DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         SERIAL PRIMARY KEY   NOT NULL,
    username   TEXT UNIQUE          NOT NULL,
    enabled    BOOLEAN DEFAULT TRUE NOT NULL,
    password   TEXT                 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE authorities
(
    id         SERIAL PRIMARY KEY NOT NULL,
    username   TEXT               NOT NULL,
    authority  TEXT               NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

CREATE TABLE carts
(
    id          SERIAL PRIMARY KEY NOT NULL,
    user_id     INTEGER UNIQUE     NOT NULL,
    total_price NUMERIC            NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id                  SERIAL PRIMARY KEY NOT NULL,
    menu_item_object_id TEXT               NOT NULL, -- 存储 MongoDB 的 ObjectId
    cart_id             INTEGER            NOT NULL,
    price               NUMERIC            NOT NULL,
    quantity            INTEGER            NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE CASCADE
);

INSERT INTO users (username, password) VALUES
                                           ('user1', 'password1'),
                                           ('user2', 'password2'),
                                           ('user3', 'password3');

-- 插入测试数据到 authorities 表
INSERT INTO authorities (username, authority) VALUES
                                                  ('user1', 'ROLE_USER'),
                                                  ('user2', 'ROLE_USER'),
                                                  ('user3', 'ROLE_ADMIN');

-- 插入测试数据到 carts 表
INSERT INTO carts (user_id, total_price) VALUES
                                             (1, 100.00),
                                             (2, 200.50),
                                             (3, 150.75);

-- 插入测试数据到 orders 表
INSERT INTO orders (menu_item_object_id, cart_id, price, quantity) VALUES
                                                                       ('648d1a7d89d9c48223abcd01', 1, 25.00, 2),
                                                                       ('648d1a7d89d9c48223abcd02', 2, 50.25, 3),
                                                                       ('648d1a7d89d9c48223abcd03', 3, 30.00, 1);