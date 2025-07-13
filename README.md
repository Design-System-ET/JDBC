# Programación JDBC tradicional (o JDBC nativo/puro)

## JDBC (Java Database Connectivity) es la API nativa de Java para conectarse y ejecutar operaciones en bases de datos relacionales (como MySQL, PostgreSQL, Oracle, etc.) sin usar frameworks adicionales como Hibernate o JPA.

## Caracteristicas:
- Bajo nivel: tu controlas cada detalle, conexion, SQL, ejecucion y cierre de recursos.
- Codigo explicito: escribes directamente las sentencias SQL.
- Sin capa de abstraccion: no hay mapeo automatico entre clases java y tablas.
- Mas control y responsabilidad: se deben manejar errore, cierre de conexion y validaciones manualmente.
- Requiere mas codigo: pero es mas transparente y facil de depurar.


## Detalles del proyecto
- Es un ejemplo simple de un CRUD utilizando una conexion JDBC a una base de datos MySQL, teniendo en cuenta que el schema de la BD "curso_java" debe existir y la tabla "empleados" tambien.
- La tabla "empleados" esta compuesta por 3 atributos (id, nombre, direccion):
   "id" esta definida como INT, PK, NN, AI.
   "nombre" y "direccion" estan definidos como VARCHAR(255), NN.

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
- Puedes utilizar un config.properties para definir los datos de acceso y poder ejecutar desde consola, jar o contenedor, ej:

```
URL=jdbc:mysql://localhost:3306/mi_bd
USER=mi_user
PASS=mi_pass
```

y luego modificar el resources a:

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


