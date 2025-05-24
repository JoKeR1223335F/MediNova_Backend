package com.example.demo.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPostgres {

    private static final String URL = "jdbc:postgresql://localhost:5432/medinova";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "MediNova";

    static {
        try {
            // Carga explícita del driver (opcional en JDBC 4+, pero bien para compatibilidad)
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el driver de PostgreSQL", e);
        }
    }

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
