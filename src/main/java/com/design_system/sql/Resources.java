package com.design_system.sql;

public interface Resources {
    
    //System.getenv utiliza las variables de entorno creadas con netbeans
    public static final String URL = System.getenv("URL");
    public static final String USER = System.getenv("USER");
    public static final String PASS = System.getenv("PASS");
}
