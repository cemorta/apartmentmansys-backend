CREATE TABLE rent_payments (
    id SERIAL PRIMARY KEY,
    resident_user_id INT REFERENCES resident_profiles(user_id) ON DELETE CASCADE,
    flat_owner_user_id INT REFERENCES flat_owner_profiles(user_id) ON DELETE CASCADE,
    flat_id INT REFERENCES flats(id) ON DELETE CASCADE,
    amount DECIMAL(10,2) NOT NULL,
    due_date DATE NOT NULL,
    paid_date DATE,
    status VARCHAR(15) CHECK (status IN ('pending', 'paid', 'overdue')) DEFAULT 'pending',
    payment_method VARCHAR(20) CHECK (payment_method IN ('credit_card', 'bank_transfer', 'cash')),
    transaction_id VARCHAR(100) UNIQUE,
    notes TEXT, -- For any special circumstances or additional information
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE apartment_payments (
    id SERIAL PRIMARY KEY,
    resident_user_id INT REFERENCES resident_profiles(user_id) ON DELETE CASCADE,
    admin_user_id INT REFERENCES admin_profiles(user_id) ON DELETE CASCADE,
    flat_id INT REFERENCES flats(id) ON DELETE CASCADE,
    amount DECIMAL(10,2) NOT NULL,
    due_date DATE NOT NULL,
    paid_date DATE,
    payment_type VARCHAR(15) CHECK (payment_type IN ('dues', 'payments')) DEFAULT 'dues',
    status VARCHAR(15) CHECK (status IN ('pending', 'paid', 'overdue')) DEFAULT 'pending',
    payment_method VARCHAR(20) CHECK (payment_method IN ('credit_card', 'bank_transfer', 'cash')),
    transaction_id VARCHAR(100) UNIQUE,
    notes TEXT, -- For any special circumstances or additional information
    created_at TIMESTAMP DEFAULT NOW()
);
