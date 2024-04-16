-- Cria a tabela de torneios se ela não existir
CREATE TABLE IF NOT EXISTS tournamentmanager.tournaments (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    description TEXT
);

-- Cria a tabela de jogadores se ela não existir
CREATE TABLE IF NOT EXISTS tournamentmanager.players (
    player_id VARCHAR(255) NOT NULL PRIMARY KEY,
    player_name VARCHAR(255) NOT NULL,
    tournament_id VARCHAR(255),
    FOREIGN KEY (tournament_id) REFERENCES tournamentmanager.tournaments (id)
);