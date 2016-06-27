CREATE TABLE protocolos (
  id INTEGER PRIMARY KEY,
  dataCriacao DATE NOT NULL,
  dataFimExecucao DATE NULL,
  dataInicioExecucao DATE NULL,
  feedback TEXT NULL,
  identificador VARCHAR(100) NULL,
  mensagemLivre TEXT NULL,
  motivoCancelamento TEXT NULL,
  respota TEXT NULL,
  status INTEGER NOT NULL,
  area INTEGER NOT NULL,
  categoria INTEGER NOT NULL,
  idCliente INTEGER NOT NULL,
  idResponsavel INTEGER NULL,
  FOREIGN KEY(idCliente) REFERENCES pessoas(id),
  FOREIGN KEY(idResponsavel) REFERENCES pessoas(id)
 );

CREATE UNIQUE INDEX protocolos_identificador_index ON protocolos(identificador);