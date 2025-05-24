package com.example.demo.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactoEmergencia {
    private Integer idContacto;
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String parentesco;
    private String telefono;
    private String correo;
}
