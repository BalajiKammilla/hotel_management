DROP TABLE IF EXISTS customer_details;
DROP TABLE IF EXISTS room_details;
DROP TABLE IF EXISTS payment_details;

CREATE TABLE customer_details (
    id BIGSERIAL PRIMARY KEY,
    customerName VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    mobileNumber VARCHAR(15) NOT NULL,
    countryCode VARCHAR(5) NOT NULL,
    address VARCHAR(255) NOT NULL,
    idProof VARCHAR(50) NOT NULL,
    maritalStatus VARCHAR(20) NOT NULL
);

CREATE TABLE room_details (
    id BIGSERIAL PRIMARY KEY,
    roomNo BIGINT NOT NULL,
    roomType VARCHAR(50) NOT NULL,
    roomStatus VARCHAR(50) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    checkInType VARCHAR(50) NOT NULL,
    idProofType VARCHAR(50) NOT NULL,
    checkoutTime TIMESTAMP
);

CREATE TABLE payment_details (
    id BIGSERIAL PRIMARY KEY,
    stay_days DOUBLE PRECISION NOT NULL,
    total_price DOUBLE PRECISION NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_status VARCHAR(50) NOT NULL
);

INSERT INTO customer_details (customerName, age, mobileNumber, countryCode, address, idProof, maritalStatus)
VALUES 
    ('John Doe', 30, '1234567890', 'IN', '123 Main St, City', 'Aadhar123', 'MARRIED');

INSERT INTO room_details (roomNo, roomType, roomStatus, price, checkInType, idProofType, checkoutTime)
VALUES
    (101, 'DELUXE', 'AVAILABLE', 2500.00, 'WALK_IN', 'Aadhar', '2025-01-10 12:00:00');
    
INSERT INTO payment_details (stay_days, total_price, payment_method, payment_status)
VALUES
    (2.5, 5000.00, 'CREDIT_CARD', 'SUCCESS');    