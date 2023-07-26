CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    score INT DEFAULT 0,
    streak INT DEFAULT 1,
    total_score INT DEFAULT 0,
    longest_streak INT DEFAULT 0,
    best_overall_score INT DEFAULT 0,
    best_score_album INT DEFAULT 0,
    best_score_song INT DEFAULT 0
);