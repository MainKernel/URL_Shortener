--- All passwords in presentation mod is 'password' -----
INSERT INTO users (id,username, password, role, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES
    (1,'john_doe', '$2b$12$3ItQ6VavtYCgt3YEcCjvyOeXkbeblc97ugHukt8wMQmz.r5zbb9GS', 'USER', true, true, true, true);

INSERT INTO urls (id,short_url, long_url, user_id, visited, created_at, expired_at)
VALUES
    (1,'ab22', 'https://github.com/MainKernel', 1, 100, '2024-06-01 10:00:00', '2024-06-15 10:00:00'),
    (2,'aa22','https://google.com', 1, 2,'2024-06-01 10:00:00', '2024-06-03 10:00:00') ;