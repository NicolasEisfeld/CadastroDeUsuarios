-- V3: Adicionar campos necessários para funcionalidade de login
-- Adicionar colunas para suporte ao Spring Security UserDetails

ALTER TABLE tb_usuarios
ADD COLUMN enabled BOOLEAN DEFAULT TRUE NOT NULL;

ALTER TABLE tb_usuarios
ADD COLUMN account_non_expired BOOLEAN DEFAULT TRUE NOT NULL;

ALTER TABLE tb_usuarios
ADD COLUMN account_non_locked BOOLEAN DEFAULT TRUE NOT NULL;

ALTER TABLE tb_usuarios
ADD COLUMN credentials_non_expired BOOLEAN DEFAULT TRUE NOT NULL;

-- Criar tabela para armazenar as roles dos usuários
CREATE TABLE tb_usuarios_roles (
    tb_usuarios_id BIGINT NOT NULL,
    role VARCHAR(255) NOT NULL,
    FOREIGN KEY (tb_usuarios_id) REFERENCES tb_usuarios(id)
);

-- Inserir role padrão USER para usuários existentes
INSERT INTO tb_usuarios_roles (tb_usuarios_id, role)
SELECT id, 'USER' FROM tb_usuarios;
