CREATE TABLE endereco (
    id              bigint NOT NULL,
    complemento     VARCHAR(255),
    numero          VARCHAR(255),
    logradouro      VARCHAR(255),
    bairro          VARCHAR(255),
    cep             VARCHAR(255),
    PRIMARY KEY ( id )
);

CREATE TABLE usuario (
    id                       bigint NOT NULL unique,
    active                   boolean NOT NULL,
    nome                     VARCHAR(255) NOT NULL,
    email                    VARCHAR(255) NOT NULL unique,
    senha                    VARCHAR(255) NOT NULL,
    cpf                      VARCHAR(255) NOT NULL,
    telefone                 VARCHAR(255),
    role                    TEXT NOT NULL,
    endereco_id              bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)

);

insert into usuario values(1, TRUE, 'adm@teste', true,  'adm', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'
, '11987634487',  NULL);
-- senha 123456

--INSERT INTO usuario values
--(id, active, cpf, email, nome, "role", senha, telefone, endereco_id, confirm_password)
--VALUES(nextval('usuario_id_seq'::regclass), false, '45214479883', 'digao@', 'testando', 0, '54321', '11934483009',0, '54321');
CREATE TABLE cliente (
    id                       bigint NOT NULL,
    active                   boolean NOT NULL,
    nome                     VARCHAR(255) NOT NULL,
    email                    VARCHAR(255) NOT NULL,
    senha                    VARCHAR(255) NOT NULL,
    role                    TEXT NOT NULL

    PRIMARY KEY (id)
);

CREATE TABLE produto (
    id                       bigint NOT NULL,
    insertion_date           TIMESTAMP,
    active                   boolean NOT NULL,
    nome                     VARCHAR(255) NOT NULL,
    descri                   TEXT,
    qtd                      INT,
    valor                    NUMERIC(10,9),
    avaliacao                NUMERIC(5,9)
    PRIMARY KEY (id)
);

CREATE TABLE pedido (
    id                       bigint NOT NULL,
    insertion_date           TIMESTAMP,
    active                   boolean NOT NULL,
    client_id                bigint,
    produto_id               bigint,
    PRIMARY KEY (id)
);