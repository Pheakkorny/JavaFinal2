-- SQLite database schema
CREATE TABLE Customer (
    customer_id INTEGER PRIMARY KEY,
    customer_last_name TEXT,
    customer_first_name TEXT,
    customer_phone TEXT
);

INSERT INTO Customer (customer_id, customer_last_name, customer_first_name, customer_phone) VALUES
(1, 'Chenda', 'Sovisal', '0934823782'),
(2, 'Kom', 'Lina', '0383238233'),
(3, 'Chan', 'Seyha', '0238238233');
