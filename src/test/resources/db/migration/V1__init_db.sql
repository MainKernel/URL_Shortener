CREATE TABLE IF NOT EXISTS users(
                                    id BIGSERIAL PRIMARY KEY,
                                    username VARCHAR(64),
                                    password VARCHAR(512),
                                    role VARCHAR(12),
                                    is_account_non_expired BOOLEAN,
                                    is_account_non_locked BOOLEAN,
                                    is_credentials_non_expired BOOLEAN,
                                    is_enabled BOOLEAN
);

CREATE TABLE IF NOT EXISTS urls(
                                   id BIGSERIAL PRIMARY KEY,
                                   short_url VARCHAR(12),
                                   long_url VARCHAR(256),
                                   user_id INTEGER REFERENCES users(id),
                                   visited BIGINT,
                                   created_at TIMESTAMP DEFAULT current_timestamp,
                                   expired_at TIMESTAMP DEFAULT current_timestamp + INTERVAL '14 days'
);