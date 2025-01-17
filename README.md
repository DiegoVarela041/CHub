# CHub

CHub es una aplicación diseñada para gestionar cursos, usuarios, tópicos y respuestas de manera eficiente. Este proyecto está construido utilizando el framework **Spring Boot**, lo que lo hace modular, seguro y fácil de escalar. Es ideal para administrar contenido educativo o foros colaborativos.

## Características

- **Gestión de Cursos:**
  - Crear, leer, actualizar y eliminar (CRUD) información de cursos.
  - Gestión del estado de los cursos.

- **Gestión de Usuarios:**
  - Registro y autenticación de usuarios.
  - Validación de credenciales.

- **Gestión de Tópicos y Respuestas:**
  - Creación de tópicos y gestión de sus respuestas asociadas.
  - Estructuración de debates o discusiones.

- **Seguridad:**
  - Configuración personalizada de seguridad con filtros para autenticación y autorización.
  - Generación y validación de tokens JWT.

## Estructura del Proyecto

El proyecto sigue una estructura limpia y modular:

```
CHub
├── src
│   ├── main
│   │   ├── java/com/hackhub
│   │   │   ├── controller      # Controladores para manejar solicitudes HTTP
│   │   │   ├── DTO             # Clases de transferencia de datos
│   │   │   ├── persistence     # Modelos y repositorios para la base de datos
│   │   │   ├── service         # Servicios con la lógica de negocio
│   │   │   ├── util            # Utilidades y excepciones personalizadas
│   │   ├── resources
│   │       ├── application.properties # Configuraciones de la aplicación
│   │       ├── db/migration           # Scripts de migración para la base de datos
│   ├── test                          # Pruebas unitarias
├── pom.xml                           # Configuración de Maven
└── README.md                         # Documentación del proyecto
```

## Instalación y Configuración

Sigue estos pasos para ejecutar el proyecto en tu máquina local:

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/tu_usuario/Ckhub.git
   cd Chub
   ```

2. **Configura la base de datos:**
   - Asegúrate de tener instalado MySQL.
   - Crea una base de datos llamada `Ckhub`.
   - Configura las credenciales en el archivo `application.properties`.

3. **Ejecuta las migraciones:**

   ```bash
   ./mvnw flyway:migrate
   ```

4. **Ejecuta la aplicación:**

   ```bash
   ./mvnw spring-boot:run
   ```

5. Accede a la aplicación en `http://localhost:8080`.

## Endpoints Principales

### Usuarios
- `POST /usuarios/registrar`: Registra un nuevo usuario.
- `POST /usuarios/autenticar`: Autentica un usuario y genera un token.

### Cursos
- `GET /cursos`: Obtiene todos los cursos.
- `POST /cursos`: Crea un nuevo curso.

### Tópicos
- `GET /topicos`: Obtiene todos los tópicos.
- `POST /topicos`: Crea un nuevo tópico.

### Respuestas
- `GET /respuestas`: Obtiene todas las respuestas.
- `POST /respuestas`: Crea una nueva respuesta.

## Tecnologías Utilizadas

- **Java**
- **Spring Boot**
- **JWT (JSON Web Tokens)**
- **Flyway** (para migraciones de base de datos)
- **MySQL** (base de datos)
- **Maven** (gestión de dependencias)

## Contribuciones

¡Las contribuciones son bienvenidas! Si deseas contribuir, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama con tu característica o corrección de errores:
   ```bash
   git checkout -b nombre-de-la-rama
   ```
3. Realiza los cambios necesarios y haz un commit:
   ```bash
   git commit -m "Descripción de los cambios"
   ```
4. Envía tus cambios a tu repositorio remoto:
   ```bash
   git push origin nombre-de-la-rama
   ```
5. Abre un pull request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

