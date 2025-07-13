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

'''java
public interface Resources {
    
    //System.getenv utiliza las variables de entorno creadas con netbeans
    public static final String URL = System.getenv("URL");
    public static final String USER = System.getenv("USER");
    public static final String PASS = System.getenv("PASS");
}
