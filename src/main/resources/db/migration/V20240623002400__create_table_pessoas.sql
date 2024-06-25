CREATE TABLE users (
    id UUID PRIMARY KEY,
    document_identity VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(15),
    email VARCHAR(100),
    password VARCHAR(100),
    type VARCHAR(100) NOT NULL
);