CREATE DATABASE IF NOT EXISTS forcaDB;
USE forcaDB;

CREATE TABLE IF NOT EXISTS jogo_da_forca_resultados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    letras_chutadas VARCHAR(255),
    palavra_escolhida VARCHAR(255),
    tentativas_necessarias INT
);
