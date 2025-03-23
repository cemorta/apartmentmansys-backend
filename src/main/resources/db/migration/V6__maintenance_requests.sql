CREATE TABLE maintenance_requests (
    id SERIAL PRIMARY KEY,
    resident_user_id INT REFERENCES resident_profiles(user_id) ON DELETE CASCADE,
    flat_id INT REFERENCES flats(id) ON DELETE CASCADE,
    description TEXT NOT NULL,
    category VARCHAR(30) CHECK (category IN ('plumbing', 'electrical', 'HVAC', 'appliance', 'general')),
    priority VARCHAR(10) CHECK (priority IN ('low', 'medium', 'high')) DEFAULT 'medium',
    status VARCHAR(15) CHECK (status IN ('pending', 'in_progress', 'completed', 'canceled')) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT NOW(),
    completed_at TIMESTAMP
);

CREATE TABLE maintenance_request_assignments (
    id SERIAL PRIMARY KEY,
    request_id INT REFERENCES maintenance_requests(id) ON DELETE CASCADE,
    staff_id INT REFERENCES staff_profiles(user_id) ON DELETE CASCADE,
    assigned_at TIMESTAMP DEFAULT NOW(),
    notes TEXT,
    UNIQUE (request_id, staff_id)
);
