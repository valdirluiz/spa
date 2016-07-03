
 CREATE TABLE pessoas (
  id INTEGER PRIMARY KEY,
  cpf varchar(11) NOT NULL,
  email varchar(150),
  nome varchar(100),
  senha varchar(32),
  telefone varchar(20),
  tipo_usuario int,
  is_cliente int,
  data_cadastro date
 );

CREATE UNIQUE INDEX pessoas_id_tipo_usuario_uindex ON pessoas(id, is_cliente);

INSERT INTO pessoas (cpf, email, nome, senha, telefone, tipo_usuario, is_cliente) VALUES ('663.527.824-93', 'gerente@email.com', 'Gerente', '123', '5555', 4, 0);

UPDATE pessoas SET senha="202cb962ac59075b964b07152d234b70" WHERE id=2