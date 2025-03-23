CREATE TABLE amenities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    max_capacity INT CHECK (max_capacity > 0),
    available_start TIME NOT NULL,
    available_end TIME NOT NULL,
    CONSTRAINT chk_available_time_range CHECK (available_start <= available_end)
);

CREATE TABLE amenity_reservations (
    id SERIAL PRIMARY KEY,
    resident_id INT REFERENCES resident_profiles(user_id) ON DELETE CASCADE,
    amenity_id INT REFERENCES amenities(id) ON DELETE CASCADE,
    reservation_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status VARCHAR(15) CHECK (status IN ('pending', 'confirmed', 'canceled')) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT valid_timespan CHECK (end_time > start_time)
);

CREATE TABLE notifications (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    message TEXT NOT NULL,
    type VARCHAR(20) CHECK (type IN ('rent_reminder', 'maintenance_update', 'booking_confirmation', 'general')),
    status VARCHAR(10) CHECK (status IN ('sent', 'pending')) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT NOW()
);
