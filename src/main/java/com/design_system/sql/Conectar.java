package com.design_system.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar implements Resources{

    //implemento url, user y pass desde la interface resources 
    //en vez de definir variables aqui en la clase
    public Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa");
            return conn;
        } catch (SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
            return null;
        }
    }
}
