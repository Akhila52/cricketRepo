-- Create schema
CREATE DATABASE IF NOT EXISTS cricket;
USE cricket;

-- Teams table
CREATE TABLE teams (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_country VARCHAR(100) NOT NULL,
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
    age INT,
    team_id INT,
    created_by INT,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by INT,
    updated_timestamp TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(id)
);

-- Match Results table
CREATE TABLE matchresults (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team1_id INT NOT NULL,
    team2_id INT NOT NULL,
    team1_score INT NOT NULL,
    team2_score INT NOT NULL,
    team1_total_noofballs INT,
    team1_noof_4 INT,
    team1_noof_6 INT,
    team2_total_noofballs INT,
    team2_noof_4 INT,
    team2_noof_6 INT,
    created_by INT,
    created_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by INT,
    updated_timestamp TIMESTAMP,
    FOREIGN KEY (team1_id) REFERENCES teams(id),
    FOREIGN KEY (team2_id) REFERENCES teams(id)
);
