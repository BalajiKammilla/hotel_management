DROP TABLE IF EXISTS customer_logs;
DROP TABLE IF EXISTS customer_details;
DROP TABLE IF EXISTS room_details;
DROP TABLE IF EXISTS payment_details;

CREATE TABLE customer_details (
    id BIGSERIAL PRIMARY KEY,
    customerName VARCHAR(255) NOT NULL,
    customerID VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    mobileNumber VARCHAR(15) NOT NULL,
    password VARCHAR(255) NOT NULL,
    countryCode VARCHAR(5) NOT NULL,
    address VARCHAR(255) NOT NULL,
    idProof VARCHAR(50) NOT NULL,
    maritalStatus VARCHAR(20)
);

CREATE TABLE room_details (
    id BIGSERIAL PRIMARY KEY,
    roomNo BIGINT NOT NULL,
    roomType VARCHAR(50),
    roomStatus VARCHAR(50),
    price DOUBLE PRECISION NOT NULL,
    checkInType VARCHAR(50) NOT NULL,
    idProofType VARCHAR(50) NOT NULL,
    checkoutTime TIMESTAMP
);

CREATE TABLE payment_details (
    id BIGSERIAL PRIMARY KEY,
    stay_days DOUBLE PRECISION NOT NULL,
    total_price DOUBLE PRECISION NOT NULL,
    payment_method VARCHAR(50),
    payment_status VARCHAR(50)
);
CREATE TABLE customer_logs (
    id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customer_details(id),
    log_timestamp TIMESTAMP NOT NULL,
    room_id INT REFERENCES room_details(id),
    payment_id INT REFERENCES payment_details(id)
);

INSERT INTO customer_details (customerName,customerID, age, mobileNumber, password, countryCode, address, idProof, maritalStatus)
VALUES ('John Doe','25J001', 30, '1234567890', '$2a$12$dS2C9SD1jAzVLQNABmefpeGgVvLEt3BBjGTjCWplakL/UwsS3y.2.', 'IN', '123 Main St, City', 'Aadhar123', 'MARRIED'),
('Admin','25J002', 30, '8096602533',  '$2a$12$rLk/mhDggXcja21FLKU1R.L3JrcjafyEMlMX35sAbXt75jhUX3KZa', '+91', 'Hyderabad', 'PAN', 'MARRIED');

INSERT INTO room_details (roomNo, roomType, roomStatus, price, checkInType, idProofType, checkoutTime)
VALUES
    (101, 'AC', 'AVAILABLE', 2500.00, 'WALK_IN', 'Aadhar', '2025-01-10 12:00:00');
    
INSERT INTO payment_details (stay_days, total_price, payment_method, payment_status)
VALUES
    (2.5, 5000.00, 'CREDIT_CARD', 'SUCCESS');    