CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    value NUMERIC(19, 5) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    payer_id UUID NOT NULL,
    payee_id UUID NOT NULL,
    category VARCHAR(100) NOT NULL,
    CONSTRAINT fk_payer FOREIGN KEY(payer_id) REFERENCES users(id),
    CONSTRAINT fk_payee FOREIGN KEY(payee_id) REFERENCES users(id)
);