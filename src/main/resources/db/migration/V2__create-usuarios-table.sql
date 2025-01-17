CREATE TABLE usuarios(

     id_usuario BIGINT NOT NULL AUTO_INCREMENT,
     nombre VARCHAR(100) NOT NULL,
     correo_electronico VARCHAR(100) NOT NULL UNIQUE ,
     contrasenia VARCHAR(500) NOT NULL,

     PRIMARY KEY (id_usuario)
);