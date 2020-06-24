
CREATE TABLE PAUTA (
	id_pauta SERIAL PRIMARY KEY,
	nome VARCHAR(30) NOT NULL
);

CREATE TABLE SESSAO (
  id_sessao SERIAL PRIMARY KEY,
  data_inicio TIMESTAMP NOT NULL,
  data_finalizacao TIMESTAMP NOT NULL,
  fk_pauta INTEGER NOT NULL,
  FOREIGN KEY (fk_pauta) REFERENCES PAUTA (id_pauta)
);

CREATE TABLE VOTO (
	id_voto SERIAL PRIMARY KEY,
	matricula_associado INTEGER NOT NULL,
	valor VARCHAR(10) NOT NULL,
	fk_pauta INTEGER NOT NULL,
	FOREIGN KEY (fk_pauta) REFERENCES PAUTA (id_pauta)
);