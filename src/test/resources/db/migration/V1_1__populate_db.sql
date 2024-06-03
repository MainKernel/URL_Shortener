INSERT INTO users (username, password, role, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES
    ('john_doe', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('jane_smith', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('alex_jones', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('sara_williams', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('michael_clark', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('emily_brown', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('david_miller', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('amy_jackson', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('kevin_white', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true),
    ('olivia_anderson', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true);

INSERT INTO urls (short_url, long_url, user_id, visited, created_at, expired_at)
VALUES
    ('abc123', 'https://example.com/page1', 1, 100, '2024-06-01 10:00:00', '2024-06-15 10:00:00'),
    ('def456', 'https://example.com/page2', 2, 50, '2024-06-02 09:30:00', '2024-06-16 09:30:00'),
    ('ghi789', 'https://example.com/page3', 3, 200, '2024-06-03 11:45:00', '2024-06-17 11:45:00'),
    ('jkl012', 'https://example.com/page4', 4, 80, '2024-06-04 08:15:00', '2024-06-18 08:15:00'),
    ('mno345', 'https://example.com/page5', 5, 150, '2024-06-05 14:20:00', '2024-06-19 14:20:00'),
    ('pqr678', 'https://example.com/page6', 6, 120, '2024-06-06 13:10:00', '2024-06-20 13:10:00'),
    ('stu901', 'https://example.com/page7', 7, 90, '2024-06-07 07:50:00', '2024-06-21 07:50:00'),
    ('vwx234', 'https://example.com/page8', 8, 110, '2024-06-08 12:30:00', '2024-06-22 12:30:00'),
    ('yzu567', 'https://example.com/page9', 9, 70, '2024-06-09 15:00:00', '2024-06-23 15:00:00'),
    ('123abc', 'https://example.com/page10', 10, 180, '2024-06-10 10:45:00', '2024-06-24 10:45:00');
