package com.design_system.sql;

public class main {

    public static void main(String[] args) {
       
       //instancio empleados
       Empleados_DAO crud = new Empleados_DAO();
       
       //creo un registro nuevo
       //crud.createEmpleados("Cristina", "Gral Artigas 345");
       
       //leo toda la tabla
       //crud.readEmpleados();
       
       //actualizo un registro con id 4 (indico nuevos valores y paso el id)
       //crud.updateEmpleados("MARIA", "SANCRISTOBAL", 4);
       
       
       //eliminando un registro segun id = 4
       crud.deleteEmpleados(4);
    }
}
