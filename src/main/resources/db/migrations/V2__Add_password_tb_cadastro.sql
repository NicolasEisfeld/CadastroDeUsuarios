-- V2: Migrations para adicionar a coluna 'password' na tabela 'tb_cadastro'
-- Tornar a coluna NOT NULL com um valor padrão para evitar falhas quando já existirem linhas
ALTER TABLE tb_usuarios
ADD COLUMN password VARCHAR(255) DEFAULT '' NOT NULL;
