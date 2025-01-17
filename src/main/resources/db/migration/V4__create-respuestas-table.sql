CREATE TABLE respuestas(

   id_respuesta BIGINT NOT NULL AUTO_INCREMENT,
   mensaje VARCHAR(1000) NOT NULL,
   fecha_creacion DATE NOT NULL,
   solucion VARCHAR(1000) NOT NULL,
   id_topico BIGINT NOT NULL,
   id_usuario BIGINT NOT NULL,

   PRIMARY KEY (id_respuesta),

   CONSTRAINT FOREIGN KEY (id_topico) REFERENCES topicos(id_topico),
   CONSTRAINT FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);