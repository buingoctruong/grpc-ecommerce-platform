-- "item" table
CREATE TABLE ITEM (
    ID VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    PRICE DOUBLE NOT NULL,
    WIDTH DOUBLE,
    DEPTH DOUBLE,
    HEIGHT DOUBLE
);

-- "user" table
CREATE TABLE USER (
    ID VARCHAR(36) PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    BALANCE DOUBLE NOT NULL,
    POSTAL_CODE VARCHAR(20),
    CITY VARCHAR(100),
    STATE VARCHAR(100),
    STREET VARCHAR(255),
    BUILDING_NAME VARCHAR(255),
    ROOM_NUMBER VARCHAR(50)
);

-- "inventory" table
CREATE TABLE INVENTORY (
    ID VARCHAR(36) PRIMARY KEY,
    ITEM_ID VARCHAR(36) NOT NULL,
    QUANTITIES BIGINT NOT NULL
);

-- "order" table (properly escaped)
CREATE TABLE `ORDER` (
    ID VARCHAR(36) PRIMARY KEY,
    ITEM_ID VARCHAR(36) NOT NULL,
    USER_ID VARCHAR(36) NOT NULL,
    PURCHASE_QUANTITIES INT NOT NULL,
    ORDER_AMOUNT DOUBLE NOT NULL,
    STATUS VARCHAR(20) NOT NULL
);

-- Insert sample data for ITEM table
INSERT INTO ITEM (ID, NAME, PRICE, WIDTH, DEPTH, HEIGHT) VALUES
('123e4567-e89b-12d3-a456-426614174000', 'Laptop', 999.99, 13.3, 9.0, 0.7),
('987e6543-e21b-45d3-c123-123456789abc', 'Smartphone', 699.99, 5.8, 2.8, 0.3),
('456e1234-e89b-67d3-a890-987654321cba', 'Tablet', 499.99, 10.0, 7.0, 0.4);

-- Insert default data into "inventory" table
INSERT INTO INVENTORY (ID, ITEM_ID, QUANTITIES) VALUES
('234e5678-e89b-12d3-a456-426614174001', '123e4567-e89b-12d3-a456-426614174000', 50),
('567e8901-e21b-45d3-c123-123456789def', '987e6543-e21b-45d3-c123-123456789abc', 100),
('678e3456-e89b-67d3-a890-987654321fed', '456e1234-e89b-67d3-a890-987654321cba', 75);

-- Add sample data for USER table
INSERT INTO USER (ID, NAME, BALANCE, POSTAL_CODE, CITY, STATE, STREET, BUILDING_NAME, ROOM_NUMBER) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'Taro Tanaka', 1500.00, '100-0001', 'Tokyo', NULL, 'Chiyoda', 'Tokyo Tower', '45F'),
('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Yuki Sato', 2000.00, '530-0001', 'Osaka', NULL, 'Kita', 'Osaka Station City', '12F'),
('9fd7c1ff-9ae7-4d47-a0da-1976a6b1b1b8', 'Hiroshi Yamamoto', 1200.00, '460-0002', 'Nagoya', NULL, 'Naka-ku', 'Nagoya TV Tower', '8F'),
('16fd2706-8baf-433b-82eb-8c7fada847da', 'Akiko Nakamura', 1800.00, '231-0001', 'Yokohama', NULL, 'Naka-ku', 'Landmark Tower', '23F');
