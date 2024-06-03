CREATE TABLE users(
                                    id BIGINT PRIMARY KEY,
                                    username VARCHAR(64),
                                    password VARCHAR(512),
                                    role VARCHAR(12),
                                    is_account_non_expired BOOLEAN,
                                    is_account_non_locked BOOLEAN,
                                    is_credentials_non_expired BOOLEAN,
                                    is_enabled BOOLEAN
);

CREATE TABLE urls(
                                   id BIGINT PRIMARY KEY,
                                   short_url VARCHAR(12),
                                   long_url VARCHAR(256),
                                   user_id INTEGER REFERENCES users(id),
                                   visited BIGINT,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   expired_at TIMESTAMP DEFAULT (SELECT DATEADD('DAY', 14, CURRENT_TIMESTAMP))
);

CREATE SEQUENCE USERS_SEQ START WITH 5 INCREMENT BY 1;
CREATE SEQUENCE URLS_SEQ  START WITH 5 INCREMENT BY 1;