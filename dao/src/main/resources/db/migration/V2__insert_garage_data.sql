-- Insert garages
INSERT INTO garages (name, city, address, telephone, email) VALUES
('Garage Central', 'Paris',   '12 Rue de la Paix, Paris',         '0123456789', 'central@garage.fr'),
('Garage Nord', 'Lyon' ,   '45 Avenue Victor Hugo, Lyon',       '0456789123', 'nord@garage.fr'),
('Garage Sud', 'Marseille',   '78 Boulevard Michelet, Marseille',  '0789123456', 'sud@garage.fr');

-- Garage 1 - opening times
INSERT INTO garage_opening_times (garage_id, day_of_week, start_time, end_time) VALUES
(1, 'MONDAY',    '08:00', '12:00'),
(1, 'MONDAY',    '14:00', '18:00'),
(1, 'TUESDAY',   '08:00', '12:00'),
(1, 'TUESDAY',   '14:00', '18:00'),
(1, 'WEDNESDAY', '08:00', '12:00'),
(1, 'THURSDAY',  '08:00', '12:00'),
(1, 'THURSDAY',  '14:00', '18:00'),
(1, 'FRIDAY',    '08:00', '17:00'),
(1, 'SATURDAY',  '09:00', '13:00');

-- Garage 2 - opening times
INSERT INTO garage_opening_times (garage_id, day_of_week, start_time, end_time) VALUES
(2, 'MONDAY',    '09:00', '12:30'),
(2, 'MONDAY',    '13:30', '18:00'),
(2, 'TUESDAY',   '09:00', '18:00'),
(2, 'WEDNESDAY', '09:00', '18:00'),
(2, 'THURSDAY',  '09:00', '18:00'),
(2, 'FRIDAY',    '09:00', '17:30');

-- Garage 3 - opening times
INSERT INTO garage_opening_times (garage_id, day_of_week, start_time, end_time) VALUES
(3, 'MONDAY',    '07:30', '12:00'),
(3, 'TUESDAY',   '07:30', '12:00'),
(3, 'TUESDAY',   '13:00', '18:00'),
(3, 'WEDNESDAY', '07:30', '12:00'),
(3, 'THURSDAY',  '07:30', '12:00'),
(3, 'THURSDAY',  '13:00', '18:00'),
(3, 'FRIDAY',    '07:30', '16:00'),
(3, 'SATURDAY',  '08:00', '12:00');