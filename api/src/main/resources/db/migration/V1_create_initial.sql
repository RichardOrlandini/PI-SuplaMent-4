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
    id                       bigint NOT NULL,
    active                   boolean NOT NULL,
    nome                     VARCHAR(255) NOT NULL,
    email                    VARCHAR(255) NOT NULL,
    senha                    VARCHAR(255) NOT NULL,
    telefone                 VARCHAR(255),
    role                    TEXT NOT NULL,
    endereco_id              bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

insert into usuario values(1, TRUE, 'adm@teste', true,  'adm', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'
, '11987634487',  NULL);
-- senha 123456
--INSERT INTO endereco VALUES (1, 'Rua Saloá', '123', 'Rua', 'Jardim Mutinga','05159-040');


CREATE TABLE cliente (
    id                       bigint NOT NULL,
    active                   boolean NOT NULL,
    nome                     VARCHAR(255) NOT NULL,
    email                    VARCHAR(255) NOT NULL,
    senha                    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE produto (
    id                       bigint NOT NULL,
    active                   boolean NOT NULL,
    nome                     VARCHAR(255) NOT NULL,
    descri                   TEXT,
    qtd                      INT,
    valor                    NUMERIC(10,9)
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