-- NÃO MECHER NESSE SCRIPT
DELETE FROM public.usuario WHERE id=1;
INSERT INTO USUARIO (ID, insertion_date , active, role, email, senha) VALUES (1001, CURRENT_TIMESTAMP, TRUE, 0, 'adm@adm', '123456');
