TRUNCATE TABLE courier_table CASCADE;
TRUNCATE TABLE delivery_table CASCADE;
TRUNCATE TABLE parcel_table CASCADE;
TRUNCATE TABLE route_table CASCADE;
TRUNCATE TABLE user_table CASCADE;
TRUNCATE TABLE address_table CASCADE;

-- Inserting data into courier_table
INSERT INTO courier_table (email, name, phone, status, vehicle)
VALUES ('john@example.com', 'John Doe', '+48 123 456 789', 'AVAILABLE', 'bike'),
       ('jane@example.com', 'Jane Smith', '+48 987 654 321', 'ON_DELIVERY', 'car');

-- Inserting data into parcel_table
INSERT INTO parcel_table (dimensions, name, status, weight, courier_id)
VALUES ('20x10x5', 'Small package', 'CREATED', 1.5, 1),
       ('50x50x50', 'Large package', 'CREATED', 10, 2);

-- Inserting data into delivery_table
INSERT INTO delivery_table (end_time, notes, start_time, status, courier_id, parcel_id)
VALUES ('2023-03-30 13:00:00', 'Delivered to the front desk', '2023-03-30 12:15:00', 'IN_PROGRESS', 1, 1),
       (NULL, 'Attempted delivery, recipient not home', '2023-03-31 16:00:00', 'IN_PROGRESS', 2, 2);

-- Inserting data into route_table
INSERT INTO route_table (latitude, longitude, route_order, courier_id, delivery_id)
VALUES (50.0646501, 19.9449799, 1, 1, 1),
       (50.0502525, 19.9443941, 1, 2, 2);

-- Inserting data into user_table
INSERT INTO user_table (email, name, password)
VALUES ('sender@example.com', 'Sender Name', 'password123'),
       ('recipient@example.com', 'Recipient Name', 'password456'),
       ('admin@example.com', 'Admin Name', 'adminpassword');

-- Inserting data into address_table
INSERT INTO address_table (city, country, latitude, longitude, postal_code, state, street, user_id)
VALUES ('Kraków', 'Poland', 50.0620054, 19.9409846, '31-000', 'Małopolskie', 'Karmelicka 27', 1),
       ('Kraków', 'Poland', 50.0496831, 19.9458382, '31-039', 'Małopolskie', 'Długa 5', 2),
       ('Kraków', 'Poland', 50.0594409, 19.9343033, '31-161', 'Małopolskie', 'Main Square 1', 3);

-- Inserting data into parcel_address_table
INSERT INTO parcel_address_table (parcel_id, address_id, role)
VALUES (1, 1, 'SENDER'),
       (1, 2, 'RECIPIENT'),
       (2, 2, 'SENDER'),
       (2, 3, 'RECIPIENT');

-- Inserting data into user_parcel_table
INSERT INTO user_parcel_table (parcel_id, user_id, role)
VALUES (1, 1, 'SENDER'),
       (1, 2, 'RECIPIENT');