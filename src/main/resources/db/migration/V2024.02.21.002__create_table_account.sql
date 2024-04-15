CREATE TABLE IF NOT EXISTS tournaments (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    description TEXT
);