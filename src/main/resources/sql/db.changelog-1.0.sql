--liquibase formatted sql

--changeset root:1
DROP TABLE IF EXISTS Procedimento;

--changeset root:2
CREATE TABLE IF NOT EXISTS Procedimento
(
    id              int(5) auto_increment,
    nroprocedimento varchar(20),
    idade           int,
    genero          varchar(9),
    permitido       boolean,
    primary key (id)
);

--chageset root:3
INSERT INTO Procedimento
    (id, nroprocedimento, idade, genero, permitido)
VALUES
    (1, '1234', 10, 'MASCULINO', 0);

INSERT INTO Procedimento
    (id, nroprocedimento, idade, genero, permitido)
VALUES
    (2, '4567', 20, 'MASCULINO', 1);

INSERT INTO Procedimento
    (id, nroprocedimento, idade, genero, permitido)
VALUES
    (3, '6789', 10, 'FEMININO', 0);

INSERT INTO Procedimento
    (id, nroprocedimento, idade, genero, permitido)
VALUES
    (4, '6789', 10, 'MASCULINO', 1);

INSERT INTO Procedimento
    (id, nroprocedimento, idade, genero, permitido)
VALUES
    (5, '1234', 20, 'MASCULINO', 1);

INSERT INTO Procedimento
    (id, nroprocedimento, idade, genero, permitido)
VALUES
    (6, '4567', 30, 'FEMININO', 1);
