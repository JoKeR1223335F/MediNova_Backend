package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Usuario {
    private Integer idUsuario;
    private String username;
    private String password;
    private String rol;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String correo;
    private String telefono;
    private boolean estado;


}