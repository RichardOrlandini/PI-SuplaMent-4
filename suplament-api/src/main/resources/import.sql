
INSERT INTO CATEGORIA (ID, DESCRICAO) VALUES (1, 'Comic Books');
INSERT INTO CATEGORIA (ID, DESCRICAO) VALUES (2, 'Movies');
INSERT INTO CATEGORIA (ID, DESCRICAO) VALUES (3, 'Books');

INSERT INTO FORNECEDOR (ID, NOME) VALUES (1, 'Panini Comics');
INSERT INTO FORNECEDOR (ID, NOME) VALUES (2, 'Amazon');

INSERT INTO public.produto (active, qtd, fk_categoria, fk_fornecedor, id, insertion_date, nome) VALUES(true, 30, 1, 1, 1, CURRENT_TIMESTAMP, 'produto1');
INSERT INTO public.produto (active, qtd, fk_categoria, fk_fornecedor, id, insertion_date, nome) VALUES(true, 60, 2, 2, 2, CURRENT_TIMESTAMP, 'produto2');

-- NÃO MECHER NESSE SCRIPT
DELETE FROM public.usuario WHERE id=1;
INSERT INTO USUARIO (ID, insertion_date , active, role, email, senha) VALUES (1, CURRENT_TIMESTAMP, TRUE, 0, 'adm@adm', '123456');
