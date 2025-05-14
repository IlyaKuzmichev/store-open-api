-- insert data to table address
INSERT INTO address (country, city, street)
VALUES ('USA', 'New York', '5th Avenue'),
       ('Germany', 'Berlin', 'Unter den Linden'),
       ('UK', 'London', 'Baker Street'),
       ('France', 'Paris', 'Champs-Élysées'),
       ('Russia', 'Moscow', 'Tverskaya Street');

-- insert data to table supplier
INSERT INTO supplier (name, address_id, phone_number)
VALUES ('Supplier A', 1, '+1-800-555-1234'),
       ('Supplier B', 2, '+49-30-1234-5678'),
       ('Supplier C', 3, '+44-20-1234-9876'),
       ('Supplier D', 4, '+33-1-2345-6789'),
       ('Supplier E', 5, '+7-495-123-4567');

-- insert data to table images
INSERT INTO image (id, image)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'some_binary_data_1'),
       ('550e8400-e29b-41d4-a716-446655440001', 'some_binary_data_2'),
       ('550e8400-e29b-41d4-a716-446655440002', 'some_binary_data_3');

-- insert data to table product
INSERT INTO product (name, category, price, available_stock, last_update_date, supplier_id, image_id)
VALUES ('Washing Machine', 'Home Appliances', 499.99, 50, '2024-01-15', 1, '550e8400-e29b-41d4-a716-446655440000'),
       ('Microwave Oven', 'Home Appliances', 129.99, 30, '2024-02-10', 2, '550e8400-e29b-41d4-a716-446655440001'),
       ('Refrigerator', 'Home Appliances', 799.99, 25, '2024-01-05', 3, '550e8400-e29b-41d4-a716-446655440002'),
       ('Air Conditioner', 'Home Appliances', 399.99, 40, '2024-02-20', 4, '550e8400-e29b-41d4-a716-446655440000'),
       ('Blender', 'Kitchen Appliances', 59.99, 100, '2024-01-25', 5, '550e8400-e29b-41d4-a716-446655440001');

-- insert data to table client
INSERT INTO client (client_name, client_surname, birthday, gender, registration_date, address_id)
VALUES ('John', 'Doe', '1985-05-15', 'M', '2024-02-01', 1),
       ('Jane', 'Smith', '1990-10-25', 'F', '2024-02-05', 2),
       ('Emily', 'Johnson', '1978-08-14', 'F', '2024-02-10', 3),
       ('Michael', 'Brown', '1982-11-30', 'M', '2024-02-15', 4),
       ('David', 'Wilson', '2000-01-01', 'M', '2024-02-20', 5);
