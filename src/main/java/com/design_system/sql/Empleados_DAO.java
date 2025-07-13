package com.design_system.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Empleados_DAO {

    //instancia de conectar
    Conectar conex = new Conectar();

    //variables con las sentencias sql que voy a utilizar para el CRUD
    String create = "INSERT INTO empleados(nombre, direccion)VALUES(?,?)";
    String read = "SELECT * FROM empleados";
    String update = "UPDATE empleados SET nombre=?, direccion=? WHERE id=?";
    String delete = " DELETE FROM empleados WHERE id=?";

    public void createEmpleados(String nombre, String direccion) {

        //try-with-resources para cerrar recursos automaticamente
        try (
                //la conexion se almacena en la variable
                Connection connection = conex.conectar(); //variable de peticion y seguridad
                //AYUDA A PREVENIR ATAQUES DE INYECCION SQL
                 PreparedStatement stmt = connection.prepareStatement(create)) {

            //Estableciendo valores
            // 1 y 2 significan las posiciones de los valores de como se
            //definieron en la variable sql con la sentencia del INSERT
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);

            //ejecuto la consulta/ingreso de datos
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Registro insertado con éxito. Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
        }
    }

    public void readEmpleados() {

        //try-with-resources para cerrar recursos automaticamente
        try (
                //la conexion se almacena en la variable
                Connection connection = conex.conectar(); 
                //declaracion de sql command
                Statement statement = connection.createStatement(); 
                //ejecutamos la clausula SELECT y guardamos el resultado
                ResultSet resultset = statement.executeQuery(read);) {

            //recorro todos los resultados de la tabla
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String nombre = resultset.getString("nombre");
                String direccion = resultset.getString("direccion");

                //para cada conjunto encontrado imprimo en consola
                System.out.println("\n " + "Id: " + id + " Nombre: " + nombre + " Direccion: " + direccion);
            }

        } catch (SQLException e) {
            System.out.println("Error sql: " + e.getMessage());
        }
    }

    public void updateEmpleados(String nombre, String direccion, int id) {

        //try-with-resources para cerrar recursos automaticamente
        try (
                //la conexion se almacena en la variable
                Connection connection = conex.conectar(); //variable de peticion y seguridad
                //AYUDA A PREVENIR ATAQUES DE INYECCION SQL
                PreparedStatement stmt = connection.prepareStatement(update)) {

            //Estableciendo valores
            // 1 y 2 significan las posiciones de los valores de como se
            //definieron en la variable sql con la sentencia del UPDATE
            stmt.setString(1, nombre);
            stmt.setString(2, direccion);
            stmt.setInt(3, id);

            //ejecuto la actualizacion
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Se actualizo la fila correspondiente.\n " + "Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            System.out.println("Error - A ocurrido un error: " + e.getMessage());
        }

    }

    public void deleteEmpleados(int id) {

        try (
                //la conexion se almacena en la variable
                Connection connection = conex.conectar(); //variable de peticion y seguridad
                //AYUDA A PREVENIR ATAQUES DE INYECCION SQL
                PreparedStatement stmt = connection.prepareStatement(delete)) {

            //Estableciendo valores
            // 1 significa lasposicione del valores  como se
            //definio en la variable sql con la sentencia del DELETE
            stmt.setInt(1, id);

            //ejetuto la eliminacion
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Se elimino correctamente el registro");

        } catch (SQLException e) {
            System.out.println("Error - no se pudo realizar la eliminacion: " + e.getMessage());
        }
    }
}
