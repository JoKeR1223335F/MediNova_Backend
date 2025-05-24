package com.example.demo.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPostgres {
    private static final String URL="jdbc:postgresql://localhost:5432/medinova";
 private static final String USERNAME="postgres";
 private static final String PASSWORD="MediNova";
    public static Connection getConexion() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
