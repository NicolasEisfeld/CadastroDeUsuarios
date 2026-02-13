-- Adicionar campo status na tabela tb_tarefas
ALTER TABLE tb_tarefas ADD COLUMN status VARCHAR(20) DEFAULT 'PENDENTE';
