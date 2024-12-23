-- grant_permissions.sql

-- Conceder permissão para criar usuários
GRANT CREATE USER TO cdit;

-- Conceder permissões adicionais caso necessário
GRANT ALTER SESSION TO cdit;
GRANT CREATE SESSION TO cdit;
GRANT DBA TO cdit;  -- Opcional, concedendo permissões de DBA
