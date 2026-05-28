CREATE TABLE (
    id BIGSERIAL primary key ,
    nome VARCHAR(100) NOT NULL ,
    descricao VARCHAR(200) NOT NULL,
    preco NUMERIC(15, 2) NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    estoque INTEGER NOT NULL
);