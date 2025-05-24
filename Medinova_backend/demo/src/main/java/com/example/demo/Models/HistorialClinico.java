package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class HistorialClinico {
    private Integer idHC;
    private Integer idUsuario;
    private Timestamp fecha;
    private String observaciones;
}

