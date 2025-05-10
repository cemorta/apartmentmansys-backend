-- Seed data for apartment management system

-- Insert users (mix of admins, residents, staff and owners) The password is: "pass"
INSERT INTO users (first_name, last_name, email, phone, password_hash, created_at) VALUES
-- Admins
('John', 'Smith', 'john.smith@example.com', '+1-551-123-4567', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-01-05 09:00:00'),
('Sarah', 'Johnson', 'sarah.johnson@example.com', '+1-525-234-5678', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-01-10 10:15:00'),
-- Staff
('Michael', 'Garcia', 'michael.garcia@example.com', '+1-355-345-6789', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-01-15 11:30:00'),
('Emily', 'Williams', 'emily.williams@example.com', '+1-455-456-7890', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-01-20 12:45:00'),
('David', 'Brown', 'david.brown@example.com', '+1-555-517-8901', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-01-25 14:00:00'),
-- Flat Owners
('Jennifer', 'Davis', 'jennifer.davis@example.com', '+1-135-678-9012', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-02-01 15:15:00'),
('Robert', 'Miller', 'robert.miller@example.com', '+1-995-789-0123', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-02-05 16:30:00'),
('Lisa', 'Wilson', 'lisa.wilson@example.com', '+1-555-000-1234', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-02-10 17:45:00'),
-- Residents
('James', 'Taylor', 'james.taylor@example.com', '+1-595-901-2345', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-02-15 09:00:00'),
('Mary', 'Anderson', 'mary.anderson@example.com', '+1-255-012-3456', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-02-20 10:15:00'),
('Thomas', 'Jackson', 'thomas.jackson@example.com', '+1-455-123-4567', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-02-25 11:30:00'),
('Patricia', 'White', 'patricia.white@example.com', '+1-655-234-5678', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-03-01 12:45:00'),
('Daniel', 'Harris', 'daniel.harris@example.com', '+1-575-345-6789', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-03-05 14:00:00'),
('Nancy', 'Martin', 'nancy.martin@example.com', '+1-595-456-7890', '$2a$10$TrOy6D8XaJm7V4nv7LuEKOEHuJ3NhSNr/DgspjXp0bHZeaE6r8uU6', '2024-03-10 15:15:00');

-- Insert apartments
INSERT INTO apartments (building_name, unit_number, floor) VALUES
('Sunset Towers', 'A101', 1),
('Sunset Towers', 'A102', 1),
('Sunset Towers', 'A201', 2),
('Sunset Towers', 'A202', 2),
('Ocean View', 'B101', 1),
('Ocean View', 'B102', 1),
('Ocean View', 'B201', 2),
('Ocean View', 'B202', 2),
('Mountain Heights', 'C101', 1),
('Mountain Heights', 'C102', 1);

-- Insert roles data
-- Roles already inserted in the schema, no need to add them again

-- Insert user_roles
INSERT INTO user_roles (user_id, role_id, created_at) VALUES
-- Admins
(1, 1, '2024-01-05 09:30:00'), -- John Smith is super_admin
(2, 2, '2024-01-10 10:45:00'), -- Sarah Johnson is apartment_manager
-- Staff
(3, 4, '2024-01-15 12:00:00'), -- Michael Garcia is staff
(4, 4, '2024-01-20 13:15:00'), -- Emily Williams is staff
(5, 4, '2024-01-25 14:30:00'), -- David Brown is staff
-- Flat Owners
(6, 5, '2024-02-01 15:45:00'), -- Jennifer Davis is flat_owner
(7, 5, '2024-02-05 17:00:00'), -- Robert Miller is flat_owner
(8, 5, '2024-02-10 18:15:00'), -- Lisa Wilson is flat_owner
-- Residents
(9, 3, '2024-02-15 09:30:00'), -- James Taylor is resident
(10, 3, '2024-02-20 10:45:00'), -- Mary Anderson is resident
(11, 3, '2024-02-25 12:00:00'), -- Thomas Jackson is resident
(12, 3, '2024-03-01 13:15:00'), -- Patricia White is resident
(13, 3, '2024-03-05 14:30:00'), -- Daniel Harris is resident
(14, 3, '2024-03-10 15:45:00'); -- Nancy Martin is resident

-- Insert resident profiles
INSERT INTO resident_profiles (user_id, emergency_contact) VALUES
(9, 'Emma Taylor, +1-555-987-6543'), -- James Taylor
(10, 'John Anderson, +1-555-876-5432'), -- Mary Anderson
(11, 'Susan Jackson, +1-555-765-4321'), -- Thomas Jackson
(12, 'Christopher White, +1-555-654-3210'), -- Patricia White
(13, 'Rachel Harris, +1-555-543-2109'), -- Daniel Harris
(14, 'Peter Martin, +1-555-432-1098'); -- Nancy Martin

-- Insert admin profiles
INSERT INTO admin_profiles (user_id, role_level) VALUES
(1, 'super_admin'), -- John Smith manages all
(2, 'apartment_manager'); -- Sarah Johnson manages first 5

INSERT INTO apartment_ownerships (admin_user_id, apartment_id, start_date, is_active)
VALUES
    (2, 1, '2025-01-01 00:00:00', TRUE),
    (2, 2, '2025-01-01 00:00:00', TRUE),
    (2, 3, '2025-01-01 00:00:00', TRUE),
    (2, 4, '2025-01-15 00:00:00', TRUE),
    (2, 5, '2025-01-15 00:00:00', TRUE);

-- Insert staff profiles
INSERT INTO staff_profiles (user_id, specialization, hire_date) VALUES
(3, 'PLUMBING', '2023-12-01'), -- Michael Garcia
(4, 'ELECTRICAL', '2023-12-15'), -- Emily Williams
(5, 'GENERAL', '2024-01-02'); -- David Brown

-- Insert flat owner profiles
INSERT INTO flat_owner_profiles (user_id) VALUES
(6), -- Jennifer Davis
(7), -- Robert Miller
(8); -- Lisa Wilson

-- Insert flats with owner reference
INSERT INTO flats (apartment_id, flat_number, floor_number, area, num_bedrooms, num_bathrooms, owner_user_id, created_at) VALUES
(1, 'A101-1', 1, 120, 2, 1, 6, '2024-01-06 10:00:00'),
(1, 'A101-2', 1, 220, 3, 2, 6, '2024-01-06 10:30:00'),
(2, 'A102-1', 1, 420, 1, 1, 7, '2024-01-11 11:00:00'),
(3, 'A201-1', 1, 220, 2, 2, 7, '2024-01-16 12:30:00'),
(4, 'A202-1', 1, 220, 3, 2, 8, '2024-01-21 13:45:00'),
(5, 'B101-1', 1, 120, 2, 1, 8, '2024-01-26 15:00:00'),
(6, 'B102-1', 1, 120, 2, 2, 6, '2024-02-02 16:15:00'),
(7, 'B201-1', 1, 120, 3, 2, 7, '2024-02-07 17:30:00'),
(8, 'B202-1', 1, 120, 1, 1, 8, '2024-02-12 18:45:00'),
(9, 'C101-1', 1, 120, 2, 1, 6, '2024-02-17 10:00:00');

-- Insert flat ownerships
INSERT INTO flat_ownerships (flat_id, owner_user_id, purchase_date, ownership_percentage) VALUES
(1, 6, '2023-12-01', 100.00), -- Jennifer Davis owns A101-1
(2, 6, '2023-12-01', 100.00), -- Jennifer Davis owns A101-2
(3, 7, '2023-12-15', 100.00), -- Robert Miller owns A102-1
(4, 7, '2023-12-15', 100.00), -- Robert Miller owns A201-1
(5, 8, '2024-01-02', 100.00), -- Lisa Wilson owns A202-1
(6, 8, '2024-01-02', 100.00), -- Lisa Wilson owns B101-1
(7, 6, '2023-12-01', 100.00), -- Jennifer Davis owns B102-1
(8, 7, '2023-12-15', 100.00), -- Robert Miller owns B201-1
(9, 8, '2024-01-02', 100.00), -- Lisa Wilson owns B202-1
(10, 6, '2023-12-01', 100.00); -- Jennifer Davis owns C101-1

-- Insert flat occupants (residents)
INSERT INTO flat_occupants (flat_id, resident_user_id, lease_start_date, lease_end_date, is_primary) VALUES
(1, 9, '2024-01-15', '2025-01-14', true), -- James Taylor in A101-1
(2, 10, '2024-01-20', '2025-01-19', true), -- Mary Anderson in A101-2
(3, 11, '2024-01-25', '2025-01-24', true), -- Thomas Jackson in A102-1
(4, 12, '2024-02-01', '2025-01-31', true), -- Patricia White in A201-1
(5, 13, '2024-02-05', '2025-02-04', true), -- Daniel Harris in A202-1
(6, 14, '2024-02-10', '2025-02-09', true); -- Nancy Martin in B101-1

-- Insert rent payments
INSERT INTO rent_payments (resident_user_id, flat_owner_user_id, flat_id, amount, due_date, paid_date, status, payment_method, transaction_id, notes) VALUES
(9, 6, 1, 1200.00, '2024-02-01', '2024-01-31', 'paid', 'bank_transfer', 'TXN-2024-0131-001', 'Paid on time'),
(10, 6, 2, 1500.00, '2024-02-01', '2024-01-30', 'paid', 'credit_card', 'TXN-2024-0130-002', 'Paid early'),
(11, 7, 3, 900.00, '2024-02-01', '2024-02-01', 'paid', 'bank_transfer', 'TXN-2024-0201-003', 'Paid on due date'),
(12, 7, 4, 1300.00, '2024-02-01', '2024-02-05', 'paid', 'bank_transfer', 'TXN-2024-0205-004', 'Paid 5 days late'),
(13, 8, 5, 1600.00, '2024-02-01', NULL, 'overdue', NULL, NULL, 'Not paid yet'),
(14, 8, 6, 1100.00, '2024-02-01', '2024-01-29', 'paid', 'credit_card', 'TXN-2024-0129-005', 'Paid early'),
(9, 6, 1, 1200.00, '2024-03-01', '2024-02-28', 'paid', 'bank_transfer', 'TXN-2024-0228-006', 'Paid on time'),
(10, 6, 2, 1500.00, '2024-03-01', '2024-02-29', 'paid', 'credit_card', 'TXN-2024-0229-007', 'Paid on time'),
(11, 7, 3, 900.00, '2024-03-01', NULL, 'overdue', NULL, NULL, 'Not paid yet'),
(12, 7, 4, 1300.00, '2024-03-01', '2024-03-01', 'paid', 'cash', 'TXN-2024-0301-008', 'Paid in cash'),
(13, 8, 5, 1600.00, '2024-03-01', '2024-02-25', 'paid', 'bank_transfer', 'TXN-2024-0225-009', 'Paid early'),
(14, 8, 6, 1100.00, '2024-03-01', NULL, 'pending', NULL, NULL, 'Payment expected soon');

-- Insert apartment payments
INSERT INTO apartment_payments (resident_user_id, admin_user_id, flat_id, amount, due_date, paid_date, payment_type, status, payment_method, transaction_id, notes) VALUES
(9, 2, 1, 150.00, '2024-02-15', '2024-02-14', 'dues', 'paid', 'bank_transfer', 'APTX-2024-0214-001', 'Monthly maintenance fee'),
(10, 2, 2, 150.00, '2024-02-15', '2024-02-13', 'dues', 'paid', 'credit_card', 'APTX-2024-0213-002', 'Monthly maintenance fee'),
(11, 2, 3, 100.00, '2024-02-15', '2024-02-15', 'dues', 'paid', 'bank_transfer', 'APTX-2024-0215-003', 'Monthly maintenance fee'),
(12, 2, 4, 150.00, '2024-02-15', '2024-02-20', 'dues', 'paid', 'cash', 'APTX-2024-0220-004', 'Monthly maintenance fee - paid late'),
(13, 2, 5, 150.00, '2024-02-15', NULL, 'dues', 'overdue', NULL, NULL, 'Monthly maintenance fee - not paid'),
(14, 2, 6, 125.00, '2024-02-15', '2024-02-14', 'dues', 'paid', 'credit_card', 'APTX-2024-0214-005', 'Monthly maintenance fee'),
(9, 2, 1, 150.00, '2024-03-15', '2024-03-14', 'dues', 'paid', 'bank_transfer', 'APTX-2024-0314-006', 'Monthly maintenance fee'),
(10, 2, 2, 150.00, '2024-03-15', '2024-03-13', 'dues', 'paid', 'credit_card', 'APTX-2024-0313-007', 'Monthly maintenance fee'),
(11, 2, 3, 100.00, '2024-03-15', NULL, 'dues', 'pending', NULL, NULL, 'Monthly maintenance fee'),
(12, 2, 4, 150.00, '2024-03-15', '2024-03-15', 'dues', 'paid', 'bank_transfer', 'APTX-2024-0315-008', 'Monthly maintenance fee'),
(13, 2, 5, 150.00, '2024-03-15', '2024-03-12', 'dues', 'paid', 'credit_card', 'APTX-2024-0312-009', 'Monthly maintenance fee'),
(14, 2, 6, 125.00, '2024-03-15', NULL, 'dues', 'pending', NULL, NULL, 'Monthly maintenance fee');

-- Insert maintenance requests
INSERT INTO maintenance_requests (resident_user_id, flat_id, description, category, priority, status, created_at, completed_at) VALUES
(9, 1, 'Leaking faucet in kitchen', 'PLUMBING', 'MEDIUM', 'COMPLETED', '2024-02-05 09:00:00', '2024-02-06 14:30:00'),
(10, 2, 'Power outlet not working in bedroom', 'ELECTRICAL', 'HIGH', 'COMPLETED', '2024-02-10 10:15:00', '2024-02-10 16:45:00'),
(11, 3, 'Heating not working properly', 'HVAC', 'HIGH', 'COMPLETED', '2024-02-15 11:30:00', '2024-02-17 10:20:00'),
(12, 4, 'Dishwasher not draining', 'APPLIANCE', 'MEDIUM', 'COMPLETED', '2024-02-20 12:45:00', '2024-02-21 13:10:00'),
(13, 5, 'Ceiling light flickering', 'ELECTRICAL', 'LOW', 'IN_PROGRESS', '2024-02-25 14:00:00', NULL),
(14, 6, 'Bathroom door handle loose', 'GENERAL', 'LOW', 'PENDING', '2024-03-01 15:15:00', NULL),
(9, 1, 'Garbage disposal not working', 'APPLIANCE', 'MEDIUM', 'IN_PROGRESS', '2024-03-05 16:30:00', NULL),
(10, 2, 'Window won''t close properly', 'GENERAL', 'MEDIUM', 'PENDING', '2024-03-10 17:45:00', NULL);

-- Insert maintenance request assignments
INSERT INTO maintenance_request_assignments (request_id, staff_id, assigned_at, notes) VALUES
(1, 3, '2024-02-05 10:00:00', 'Assigned to plumbing specialist'),
(2, 4, '2024-02-10 11:00:00', 'Assigned to electrical specialist'),
(3, 5, '2024-02-15 12:00:00', 'Assigned to general staff for HVAC issue'),
(4, 3, '2024-02-20 13:00:00', 'Assigned to plumbing specialist for dishwasher'),
(5, 4, '2024-02-25 15:00:00', 'Assigned to electrical specialist'),
(7, 5, '2024-03-05 17:00:00', 'Assigned to general staff for appliance issue');

-- Insert amenities
INSERT INTO amenities (name, description, max_capacity, available_start, available_end) VALUES
('Swimming Pool', 'Outdoor pool with lounging area', 30, '08:00:00', '22:00:00'),
('Fitness Center', 'Fully equipped gym with cardio and weight machines', 20, '06:00:00', '23:00:00'),
('Community Room', 'Large room for community events and private gatherings', 50, '09:00:00', '21:00:00'),
('Tennis Court', 'Full-size tennis court with night lighting', 4, '07:00:00', '22:00:00'),
('BBQ Area', 'Outdoor BBQ grills and picnic tables', 25, '10:00:00', '22:00:00');

-- Insert amenity reservations
INSERT INTO amenity_reservations (resident_id, amenity_id, reservation_date, start_time, end_time, status, created_at) VALUES
(9, 3, '2024-03-15', '18:00:00', '20:00:00', 'confirmed', '2024-03-01 09:30:00'), -- James reserves Community Room
(10, 5, '2024-03-16', '12:00:00', '15:00:00', 'confirmed', '2024-03-02 10:45:00'), -- Mary reserves BBQ Area
(11, 4, '2024-03-17', '09:00:00', '11:00:00', 'confirmed', '2024-03-03 11:30:00'), -- Thomas reserves Tennis Court
(12, 3, '2024-03-20', '14:00:00', '16:00:00', 'pending', '2024-03-05 14:00:00'), -- Patricia requests Community Room
(13, 4, '2024-03-21', '17:00:00', '19:00:00', 'confirmed', '2024-03-06 15:45:00'), -- Daniel reserves Tennis Court
(14, 5, '2024-03-22', '11:00:00', '14:00:00', 'confirmed', '2024-03-07 16:30:00'); -- Nancy reserves BBQ Area

-- Insert notifications
INSERT INTO notifications (user_id, message, type, status, created_at) VALUES
(9, 'Your rent payment is due in 3 days', 'rent_reminder', 'sent', '2024-01-28 08:00:00'),
(10, 'Your rent payment is due in 3 days', 'rent_reminder', 'sent', '2024-01-28 08:00:00'),
(11, 'Your rent payment is due in 3 days', 'rent_reminder', 'sent', '2024-01-28 08:00:00'),
(12, 'Your rent payment is due in 3 days', 'rent_reminder', 'sent', '2024-01-28 08:00:00'),
(13, 'Your rent payment is due in 3 days', 'rent_reminder', 'sent', '2024-01-28 08:00:00'),
(14, 'Your rent payment is due in 3 days', 'rent_reminder', 'sent', '2024-01-28 08:00:00'),
(9, 'Your maintenance request has been completed', 'maintenance_update', 'sent', '2024-02-06 14:45:00'),
(10, 'Your maintenance request has been completed', 'maintenance_update', 'sent', '2024-02-10 17:00:00'),
(11, 'Your maintenance request has been completed', 'maintenance_update', 'sent', '2024-02-17 10:30:00'),
(12, 'Your maintenance request has been completed', 'maintenance_update', 'sent', '2024-02-21 13:30:00'),
(13, 'Your maintenance request is in progress', 'maintenance_update', 'sent', '2024-02-25 15:15:00'),
(9, 'Your Community Room reservation is confirmed', 'booking_confirmation', 'sent', '2024-03-01 10:00:00'),
(10, 'Your BBQ Area reservation is confirmed', 'booking_confirmation', 'sent', '2024-03-02 11:15:00'),
(11, 'Your Tennis Court reservation is confirmed', 'booking_confirmation', 'sent', '2024-03-03 12:00:00'),
(13, 'Your Tennis Court reservation is confirmed', 'booking_confirmation', 'sent', '2024-03-06 16:00:00'),
(14, 'Your BBQ Area reservation is confirmed', 'booking_confirmation', 'sent', '2024-03-07 17:00:00'),
(1, 'Monthly system maintenance scheduled for Sunday', 'general', 'sent', '2024-03-10 09:00:00'),
(2, 'Monthly system maintenance scheduled for Sunday', 'general', 'sent', '2024-03-10 09:00:00'),
(9, 'Your rent payment is now overdue', 'rent_reminder', 'pending', '2024-03-02 08:00:00'),
(12, 'Building inspection scheduled for next Tuesday', 'general', 'pending', '2024-03-12 10:30:00');
