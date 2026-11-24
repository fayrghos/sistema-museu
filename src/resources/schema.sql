CREATE TABLE IF NOT EXISTS USUARIO (
    id_usuario INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR(150) UNIQUE,
    nome VARCHAR(150),
    senha VARCHAR(255),
    ativa BOOLEAN,
    administrador BOOLEAN,
    curador BOOLEAN
);

CREATE TABLE IF NOT EXISTS CATEGORIA_OBRA (
    id_categoria_obra INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS STATUS_EXPOSICAO (
    id_status_exposicao INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS EXPOSICAO (
    id_exposicao INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_usuario INTEGER,
    id_status_exposicao INTEGER,
    titulo VARCHAR(255),
    tema VARCHAR(150),
    conceito TEXT,
    objetivos TEXT,
    descricao TEXT,
    periodo_inicio_previsto DATE,
    periodo_fim_previsto DATE,
    texto_apresentacao TEXT,

    CONSTRAINT fk_exposicao_usuario
        FOREIGN KEY (id_usuario) REFERENCES USUARIO(id_usuario),
    CONSTRAINT fk_exposicao_status
        FOREIGN KEY (id_status_exposicao) REFERENCES STATUS_EXPOSICAO(id_status_exposicao)
);

CREATE TABLE IF NOT EXISTS NUCLEO_TEMATICO (
    id_nucleo_tematico INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_exposicao INTEGER,
    titulo VARCHAR(255),
    descricao TEXT,
    texto_contextualizacao TEXT,
    ordem INTEGER,

    CONSTRAINT fk_nucleo_exposicao
        FOREIGN KEY (id_exposicao) REFERENCES EXPOSICAO(id_exposicao)
);

CREATE TABLE IF NOT EXISTS OBRA (
    id_obra INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_categoria_obra INTEGER NOT NULL,
    id_nucleo_tematico INTEGER,
    titulo VARCHAR(100),
    autoria VARCHAR(100),
    ano INTEGER,
    tecnica VARCHAR(100),
    descricao VARCHAR(1000),
    legenda VARCHAR(100),

    CONSTRAINT fk_obra_categoria
        FOREIGN KEY (id_categoria_obra) REFERENCES CATEGORIA_OBRA(id_categoria_obra),
    CONSTRAINT fk_obra_nucleo
        FOREIGN KEY (id_nucleo_tematico) REFERENCES NUCLEO_TEMATICO(id_nucleo_tematico)
);