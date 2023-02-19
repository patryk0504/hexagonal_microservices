TRUNCATE TABLE courier_table CASCADE;
TRUNCATE TABLE delivery_table CASCADE;
TRUNCATE TABLE parcel_table CASCADE;
TRUNCATE TABLE route_table CASCADE;
TRUNCATE TABLE user_table CASCADE;

-- sample data for courier_table
INSERT INTO courier_table (current_location, email, name, phone, status, vehicle)
VALUES ('Krakow Main Station', 'johndoe@example.com', 'John Doe', '123456789', 'AVAILABLE', 'Bicycle'),
       ('Krakow Market Square', 'janedoe@example.com', 'Jane Doe', '987654321', 'ON_DELIVERY', 'Car'),
       ('Rynek Podgórski 1, 30-001 Kraków, Poland', 'johnsmith@example.com', 'John Smith', '555555555', 'AVAILABLE',
        'Motorcycle'),
       ('ul. Pawińskiego 5, 31-462 Kraków, Poland', 'janesmith@example.com', 'Jane Smith', '444444444', 'AVAILABLE',
        'Bicycle'),
       ('Kraków Airport, 32-083 Balice, Poland', 'johnbrown@example.com', 'John Brown', '222222222', 'ON_DELIVERY',
        'Car'),
       ('ul. Piastowska 26, 30-070 Kraków, Poland', 'maryjones@example.com', 'Mary Jones', '333333333', 'ON_DELIVERY',
        'Motorcycle');

-- sample data for user_table
INSERT INTO user_table (email, name, password)
VALUES ('johndoe@example.com', 'John Doe', 'password123'),
       ('janedoe@example.com', 'Jane Doe', 'password456'),
       ('johnsmith@example.com', 'John Smith', 'password789'),
       ('janesmith@example.com', 'Jane Smith', 'password321'),
       ('johnbrown@example.com', 'John Brown', 'password654'),
       ('maryjones@example.com', 'Mary Jones', 'password987');


-- CREATED, IN_TRANSIT, DELIVERED, RETURNED
INSERT INTO parcel_table (delivery_date, dimensions, name, recipient_address, latitude, longitude, recipient_name,
                          sender_address, sender_name, status, weight, courier_id, user_id)
VALUES ('2023-02-21 14:00:00', '30x20x10 cm', 'Package 1', 'Plac Szczepański 1, 31-011 Kraków, Poland', 50.061952,
        19.935126, 'Jane Doe', 'ul. Floriańska 4, 31-021 Kraków, Poland', 'John Doe', 'CREATED', 1.5, 1, 1),
       ('2023-02-22 10:00:00', '25x25x25 cm', 'Package 2', 'Rynek Główny 1, 31-042 Kraków, Poland', 50.061804,
        19.936252, 'John Doe', 'ul. Szewska 25, 31-009 Kraków, Poland', 'Jane Doe', 'CREATED', 2.0, 2, 2),
       ('2023-02-23 13:00:00', '40x30x20 cm', 'Package 3', 'ul. Podwale 15, 31-046 Kraków, Poland', 50.061639,
        19.938638, 'Bob Smith', 'ul. Św. Jana 1, 31-018 Kraków, Poland', 'Alice Smith', 'CREATED', 3.0, 1, 1),
       ('2023-02-23 14:00:00', '40x30x20 cm', 'Package 3', 'ul. Zwierzyniecka 3, 31-103 Kraków, Poland', 50.058599,
        19.934439, 'Emma Smith', 'ul. św. Jana 8, 31-018 Kraków, Poland', 'John Doe', 'CREATED', 3.5, 1, 2),
       ('2023-02-24 11:00:00', '50x40x30 cm', 'Package 4', 'ul. Grodzka 59, 31-001 Kraków, Poland', 50.061422,
        19.937716, 'John Smith', 'ul. Ślusarska 11, 31-014 Kraków, Poland', 'Jane Doe', 'CREATED', 5.0, 2, 1),
       ('2023-02-25 12:00:00', '30x30x30 cm', 'Package 5', 'ul. Szewska 18, 31-009 Kraków, Poland', 50.060656,
        19.935842, 'Anna Johnson', 'ul. św. Marka 10, 31-018 Kraków, Poland', 'John Doe', 'CREATED', 2.5, 1, 1),
       ('2023-02-26 15:00:00', '25x15x15 cm', 'Package 6', 'Plac Nowy 1, 31-056 Kraków, Poland', 50.051890, 19.945545,
        'Peter Green', 'ul. Garbarska 2, 31-131 Kraków, Poland', 'Jane Doe', 'CREATED', 1.0, 2, 2),
       ('2023-02-27 10:00:00', '20x20x20 cm', 'Package 7', 'ul. Pijarska 3, 31-015 Kraków, Poland', 50.063133,
        19.938313, 'Mark Williams', 'ul. Gołębia 3, 31-007 Kraków, Poland', 'John Doe', 'CREATED', 1.5, 1, 2);


-- sample data for delivery_table
INSERT INTO delivery_table (end_time, notes, start_time, status, courier_id, parcel_id)
VALUES ('2023-02-21 16:00:00', 'Delivered to the recipient', '2023-02-21 15:00:00', 'IN_PROGRESS', 1, 1),
       ('2023-02-22 11:30:00', 'Left the package with the concierge', '2023-02-22 10:30:00', 'IN_PROGRESS', 2, 2),
       ('2023-02-23 13:30:00', 'Attempted delivery but recipient was not available', '2023-02-23 12:30:00',
        'IN_PROGRESS', 1, 3),
       ('2023-02-24 16:30:00', 'Delivered to the recipient', '2023-02-24 15:30:00', 'IN_PROGRESS', 2, 4);
