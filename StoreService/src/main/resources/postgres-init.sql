DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS cart_products;
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

CREATE TABLE cart_products (
    id SERIAL PRIMARY KEY NOT NULL,
    cart_id INTEGER NOT NULL,
    product_id VARCHAR(50) NOT NULL,
    quantity INTEGER DEFAULT 1 NOT NULL,
    CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id                  SERIAL PRIMARY KEY NOT NULL,
    user_id             INTEGER NOT NULL,
    price               NUMERIC            NOT NULL,
    payment_status      VARCHAR(20) NOT NULL ,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE order_items (
     id SERIAL PRIMARY KEY NOT NULL,
     order_id INTEGER NOT NULL,         -- constraints to order table
     product_id VARCHAR(50) NOT NULL,
     product_name VARCHAR(255) NOT NULL,
     product_price NUMERIC NOT NULL,
     quantity INTEGER NOT NULL,
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
     CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);

INSERT INTO users (username, password)
VALUES
    ('user1', '$2a$10$eBfNGK3rdGkIV6uWBy6GNe.xNcXFLw77G6wI/F7LlJe5BIHXP8lfK'),
    ('user2', '$2a$10$eBfNGK3rdGkIV6uWBy6GNe.xNcXFLw77G6wI/F7LlJe5BIHXP8lfK'),
    ('user3', '$2a$10$eBfNGK3rdGkIV6uWBy6GNe.xNcXFLw77G6wI/F7LlJe5BIHXP8lfK'),
    ('user4', '$2a$10$eBfNGK3rdGkIV6uWBy6GNe.xNcXFLw77G6wI/F7LlJe5BIHXP8lfK'),
    ('user5', '$2a$10$eBfNGK3rdGkIV6uWBy6GNe.xNcXFLw77G6wI/F7LlJe5BIHXP8lfK');

-- 插入测试数据到 authorities 表
INSERT INTO authorities (username, authority) VALUES
                                                  ('user1', 'ROLE_USER'),
                                                  ('user2', 'ROLE_USER'),
                                                  ('user3', 'ROLE_ADMIN');

-- 插入测试数据到 carts 表
INSERT INTO carts (user_id, total_price)
VALUES
    (1, 300),
    (2, 500),
    (3, 700),
    (4, 900),
    (5, 1100);

INSERT INTO cart_products (cart_id, product_id, quantity)
VALUES
-- 购物车 1 的商品
(1, '676376924b18513a04497be1', 1), -- 商品 1
(1, '676376924b18513a04497be2', 2), -- 商品 2

-- 购物车 2 的商品
(2, '676376924b18513a04497be3', 1), -- 商品 3
(2, '676376924b18513a04497be4', 2), -- 商品 4

-- 购物车 3 的商品
(3, '676376924b18513a04497be5', 1), -- 商品 5
(3, '676376924b18513a04497be6', 2), -- 商品 6

-- 购物车 4 的商品
(4, '676376924b18513a04497be7', 1), -- 商品 7
(4, '676376924b18513a04497be8', 2), -- 商品 8

-- 购物车 5 的商品
(5, '676376924b18513a04497be9', 1), -- 商品 9
(5, '676376924b18513a04497bea', 2); -- 商品 10



--
-- UPDATE carts SET  total_price = 300 WHERE id = 6;
--
-- INSERT INTO cart_products (cart_id, product_id, quantity)
-- VALUES
-- (6, '676376924b18513a04497be1', 1), -- 商品 1
-- (6, '676376924b18513a04497be2', 2); -- 商品 2
