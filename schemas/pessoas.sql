CREATE TABLE pessoas (
  id int NOT NULL,
  cpf varchar(11) NOT NULL,
  email varchar(150),
  nome varchar(100),
  senha varchar(32),
  telefone varchar(20),
  tipo_usuario int,
  data_cadastro date,
  PRIMARY KEY (id, tipo_usuario)
);