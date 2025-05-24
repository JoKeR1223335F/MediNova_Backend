package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CitaControl {
    private int idCita;
    private int idPaciente;
    private int idTratamiento;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String observaciones;
    private String estado;

    // Getters y setters
}

