CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

-- User-role relationship
CREATE TABLE user_roles (
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    role_id INT REFERENCES roles(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (user_id, role_id)
);

-- Resident-specific information
CREATE TABLE resident_profiles (
    user_id INT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    emergency_contact VARCHAR(100)
);

-- Admin-specific information
CREATE TABLE admin_profiles (
    user_id INT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    role_level VARCHAR(20) CHECK (role_level IN ('super_admin', 'apartment_manager')) DEFAULT 'apartment_manager',
    managed_apartments INT[] -- Array of apartment IDs they manage
);

-- Staff-specific information
CREATE TABLE staff_profiles (
    user_id INT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    specialization TEXT CHECK (specialization IN ('plumbing', 'electrical', 'general', 'HVAC')),
    hire_date DATE NOT NULL
);

-- Flat owner-specific information
CREATE TABLE flat_owner_profiles (
    user_id INT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    payment_details JSONB -- Flexible storage for payment information
);

-- Junction table for flat ownership
CREATE TABLE flat_ownerships (
    flat_id INT REFERENCES flats(id) ON DELETE CASCADE,
    owner_user_id INT REFERENCES flat_owner_profiles(user_id) ON DELETE CASCADE,
    purchase_date DATE NOT NULL,
    ownership_percentage DECIMAL(5,2) DEFAULT 100.00, -- Allows for co-ownership
    PRIMARY KEY (flat_id, owner_user_id)
);

-- Add owner_id to flats
ALTER TABLE flats ADD COLUMN owner_user_id INT REFERENCES flat_owner_profiles(user_id) ON DELETE SET NULL;

-- Create flat_occupants junction table
CREATE TABLE flat_occupants (
    flat_id INT REFERENCES flats(id) ON DELETE CASCADE,
    resident_user_id INT REFERENCES resident_profiles(user_id) ON DELETE CASCADE,
    lease_start_date DATE,
    lease_end_date DATE,
    is_primary BOOLEAN DEFAULT false,
    PRIMARY KEY (flat_id, resident_user_id)
);

-- Insert the possible roles
INSERT INTO roles (name) VALUES
    ('super_admin'),
    ('apartment_manager'),
    ('resident'),
    ('staff'),
    ('flat_owner');