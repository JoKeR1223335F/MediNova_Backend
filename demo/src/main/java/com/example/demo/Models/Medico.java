package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Medico {
    private Integer idMedico;
    private Integer idUsuario;
    private String especialidad;
    private String n_colegiatura;
    private String foto;
    private String observaciones;
    private BigDecimal remuneracion;

}
