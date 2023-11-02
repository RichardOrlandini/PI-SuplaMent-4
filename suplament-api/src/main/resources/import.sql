-- Inserindo categorias
INSERT INTO CATEGORIA (ID, DESCRICAO) VALUES (1000, 'Whey');
INSERT INTO CATEGORIA (ID, DESCRICAO) VALUES (1001, 'Barras');
INSERT INTO CATEGORIA (ID, DESCRICAO) VALUES (1002, 'Camisetas');

-- Inserindo fornecedor
INSERT INTO FORNECEDOR (ID, NOME) VALUES (1000, 'Suplament');

-- Inserindo produtos

-- Whey
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 30.0, 1000, 1000, 1000, 'now()', 'Whey 1', 'whey1.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 35.0, 1000, 1000, 1001, 'now()', 'Whey 2', 'whey2.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 40.0, 1000, 1000, 1002, 'now()', 'Whey 3', 'whey3.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 45.0, 1000, 1000, 1003, 'now()', 'Whey 4', 'whey4.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 50.0, 1000, 1000, 1004, 'now()', 'Whey 5', 'whey5.png');

-- Barras

INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 10.0, 1001, 1000, 1005, 'now()', 'Barra 1', 'barra1.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 15.0, 1001, 1000, 1006, 'now()', 'Barra 2', 'barra2.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 20.0, 1001, 1000, 1007, 'now()', 'Barra 3', 'barra3.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 25.0, 1001, 1000, 1008, 'now()', 'Barra 4', 'barra4.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 30.0, 1001, 1000, 1009, 'now()', 'Barra 5', 'barra5.png');
-- Camisetas

INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 50.0, 1002, 1000, 1010, 'now()', 'Camiseta 1', 'camiseta1.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date, nome, nome_imagem) VALUES (true, 20, 55.0, 1002, 1000, 1011, 'now()', 'Camiseta 2', 'camiseta2.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date,nome, nome_imagem) VALUES (true, 20, 60.0, 1002, 1000, 1012, 'now()', 'Camiseta 3', 'camiseta3.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date,nome, nome_imagem) VALUES (true, 20, 65.0, 1002, 1000, 1013, 'now()', 'Camiseta 4', 'camiseta4.png');
INSERT INTO public.produto (active, qtd, valor, fk_categoria, fk_fornecedor, id, insertion_date,nome, nome_imagem) VALUES (true, 20, 70.0, 1002, 1000, 1014, 'now()', 'Camiseta 5', 'camiseta5.png');



-- NÃO MECHER NESSE SCRIPT
DELETE FROM public.usuario WHERE id=1;
INSERT INTO USUARIO (ID, insertion_date , active, role, email, nome, senha) VALUES (1, CURRENT_TIMESTAMP, TRUE, 0, 'adm@teste', 'El patrão', '123456');
