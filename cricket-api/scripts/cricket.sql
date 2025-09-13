-- Create schema
CREATE DATABASE IF NOT EXISTS cricket;
USE cricket;

-- Teams table
CREATE TABLE teams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_country VARCHAR(100) NOT NULL
    created_by INT,
created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_by INT,
updated_timestamp TIMESTAMP

);

-- Players table
CREATE TABLE players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    speciality VARCHAR(50),
    team_id INT,
    created_by INT,
created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_by INT,
updated_timestamp TIMESTAMP

);

-- Match Results table
CREATE TABLE matchresults (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team1_id INT NOT NULL,
    team2_id INT NOT NULL,
    team1_score INT NOT NULL,
    team2_score INT NOT NULL,
    created_by INT,
created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_by INT,
updated_timestamp TIMESTAMP

  
);
