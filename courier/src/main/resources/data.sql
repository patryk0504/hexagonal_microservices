TRUNCATE TABLE courier_table CASCADE;
TRUNCATE TABLE delivery_table CASCADE;
TRUNCATE TABLE parcel_table CASCADE;
TRUNCATE TABLE route_table CASCADE;
TRUNCATE TABLE user_table CASCADE;

INSERT INTO courier_table (current_location, email, name, phone, status, vehicle)
VALUES ('Krakow Main Station', 'johndoe@example.com', 'John Doe', '123456789', 'AVAILABLE', 'Bicycle'),
       ('Krakow Market Square', 'janedoe@example.com', 'Jane Doe', '987654321', 'ON_DELIVERY', 'Car');

-- sample data for user_table
INSERT INTO user_table (email, name, password)
VALUES ('johndoe@example.com', 'John Doe', 'password123'),
       ('janedoe@example.com', 'Jane Doe', 'password456');

-- sample data for parcel_table
-- CREATED, IN_TRANSIT, DELIVERED, RETURNED
INSERT INTO parcel_table (delivery_date, dimensions, name, recipient_address, latitude, longitude, recipient_name,
                          sender_address, sender_name, status, weight, courier_id, user_id)
VALUES ('2023-02-21 14:00:00', '30x20x10 cm', 'Package 1', 'Plac Szczepański 1, 31-011 Kraków, Poland', 50.061952,
        19.935126, 'Jane Doe', 'ul. Floriańska 4, 31-021 Kraków, Poland', 'John Doe', 'CREATED', 1.5, 1, 1),
       ('2023-02-22 10:00:00', '25x25x25 cm', 'Package 2', 'Rynek Główny 1, 31-042 Kraków, Poland', 50.061804,
        19.936252, 'John Doe', 'ul. Szewska 25, 31-009 Kraków, Poland', 'Jane Doe', 'CREATED', 2.0, 2, 2);

-- sample data for delivery_table
--         IN_PROGRESS, COMPLETED
INSERT INTO delivery_table (end_time, notes, start_time, status, courier_id, parcel_id)
VALUES ('2023-02-21 16:00:00', 'Delivered to the recipient', '2023-02-21 15:00:00', 'IN_PROGRESS', 1, 1),
       ('2023-02-22 11:30:00', 'Left the package with the concierge', '2023-02-22 10:30:00', 'IN_PROGRESS', 2, 2);

-- sample data for route_table
INSERT INTO route_table (latitude, longitude, route_order, courier_id, delivery_id)
VALUES (50.061952, 19.935126, 1, 1, 1),
       (50.062054, 19.936370, 2, 1, 1),
       (50.061804, 19.936252, 1, 2, 2),
       (50.061367, 19.936250, 2, 2, 2);

-- AVAILABLE, ON_DELIVERY, OFF_DUTY

