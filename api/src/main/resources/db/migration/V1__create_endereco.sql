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
    email                    VARCHAR(255) NOT NULL,
    senha                    VARCHAR(255) NOT NULL,
    telefone                 VARCHAR(255),
    grupo                    boolean NOT NULL,
    endereco_id              bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

INSERT INTO endereco VALUES (1, 'Rua Saloá', '123', 'Rua', 'Jardim Mutinga','05159-040');
