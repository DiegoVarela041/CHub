CREATE TABLE topicos(

    id_topico BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje VARCHAR(760) NOT NULL UNIQUE,
    fecha_creacion DATE NOT NULL,
    estado BOOLEAN NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_curso BIGINT NOT NULL,

    PRIMARY KEY (id_topico),

    CONSTRAINT FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    CONSTRAINT FOREIGN KEY (id_curso) REFERENCES cursos(id_curso)
);