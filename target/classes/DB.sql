CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    score INT DEFAULT 0,
    streak INT DEFAULT 0,
    totalScore INT DEFAULT 0,
    longestStreak INT DEFAULT 0,
    bestOverallScore INT DEFAULT 0,
    bestScoreAlbum INT DEFAULT 0,
    bestScoreSong INT DEFAULT 0
);