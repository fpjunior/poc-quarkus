CREATE OR REPLACE PROCEDURE cdit.criar_login_usuario (
    p_email IN VARCHAR2,
    p_retorno OUT VARCHAR2
) AS
BEGIN
    -- Extrai o texto antes do @
    p_retorno := 'O login do usuário ' || SUBSTR(p_email, 1, INSTR(p_email, '@') - 1);
END;
/
