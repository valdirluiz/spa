CREATE TABLE clientes (
  id int NOT NULL,
  cpf varchar(11) NOT NULL,
  email varchar(150),
  nome varchar(100),
  senha varchar(32),
  telefone varchar(20),
  tipo_usuario int,
  PRIMARY KEY (id)
);