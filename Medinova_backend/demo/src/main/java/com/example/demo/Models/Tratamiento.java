package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Tratamiento {
    private Integer idTratamiento;
    private String tratamiento;
    private Integer idPaciente;
    private Integer idHC;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private byte[] archivo;
    private BigDecimal costo;
    private LocalDate fechaEliminacion;

    // Getters y setters
}

