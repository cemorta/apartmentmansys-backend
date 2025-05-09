-- Create apartments
CREATE TABLE apartments (
    id SERIAL PRIMARY KEY,
    building_name VARCHAR(50),
    unit_number VARCHAR(10) UNIQUE NOT NULL,
    floor INT
);

-- Create flats
CREATE TABLE flats (
    id SERIAL PRIMARY KEY,
    apartment_id INT REFERENCES apartments(id) ON DELETE CASCADE,
    flat_number VARCHAR(10) NOT NULL,
    floor_number INT NOT NULL,
    area INT NOT NULL,
    num_bedrooms INT CHECK (num_bedrooms > 0),
    num_bathrooms INT CHECK (num_bathrooms > 0),
    created_at TIMESTAMP DEFAULT NOW()
);
