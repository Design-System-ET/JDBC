# JDBC - Conexión a Base de Datos MySQL

Este proyecto es un ejemplo simple de un CRUD utilizando JDBC tradicional (también conocido como JDBC nativo o puro), conectándose a una base de datos MySQL.

---

## ¿Qué es JDBC tradicional?

**JDBC (Java Database Connectivity)** es la API nativa de Java para conectarse y ejecutar operaciones en bases de datos relacionales (como MySQL, PostgreSQL, Oracle, etc.) sin frameworks adicionales como Hibernate o JPA.

### Características:

- 🔹 **Bajo nivel**: tú controlas cada detalle: conexión, SQL, ejecución y cierre de recursos.
- 🔹 **Código explícito**: escribes directamente las sentencias SQL.
- 🔹 **Sin capa de abstracción**: no hay mapeo automático entre clases Java y tablas.
- 🔹 **Más control y responsabilidad**: debes manejar errores, validaciones y cierre de conexión manualmente.
- 🔹 **Requiere más código**: pero es más transparente y fácil de depurar.

---

## Detalles del Proyecto

Este ejemplo realiza un CRUD conectado a una base de datos MySQL.

- Esquema requerido: `curso_java`
- Tabla requerida: `empleados`

### Estructura de la tabla `empleados`:

| Campo     | Tipo         | Restricciones        |
|-----------|--------------|----------------------|
| id        | INT          | PK, NOT NULL, AUTO_INCREMENT |
| nombre    | VARCHAR(255) | NOT NULL             |
| direccion | VARCHAR(255) | NOT NULL             |

---


## Interfaze Resources
- Utilizamos esta para definir los datos de la conexion a la BD en MySQL. "URL", "USER" y "PASS" los definimo en las variables de entorno de Netbeans para no exponerlos directamente en el codigo, y facilitar el despliegue en servidores o contenedores.

```java
public interface Resources {
    
    //System.getenv utiliza las variables de entorno creadas con netbeans
    public static final String URL = System.getenv("URL");
    public static final String USER = System.getenv("USER");
    public static final String PASS = System.getenv("PASS");
}
```

⚠️ Importante: Estas variables solo están disponibles al ejecutar desde NetBeans (no desde consola o JAR directamente, salvo que definas también las variables en el sistema operativo).


## Archivo config.properties externo
- Puedes utilizar un config.properties para definir los datos de acceso y poder ejecutar desde consola, jar o contenedor'
- Crea un archivo `config.properties` en el directorio `src/main/resources` con el siguiente contenido:

```
URL=jdbc:mysql://localhost:3306/mi_bd
USER=mi_user
PASS=mi_pass
```

y luego modificar el Resources a:

```java
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface Resources {
    Properties props = loadProperties();

    public static final String URL = props.getProperty("URL");
    public static final String USER = props.getProperty("USER");
    public static final String PASS = props.getProperty("PASS");

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = Resources.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Archivo config.properties no encontrado en el classpath");
            }
            props.load(input);
        } catch (IOException e) {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
        }
        return props;
    }
}
```


## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

