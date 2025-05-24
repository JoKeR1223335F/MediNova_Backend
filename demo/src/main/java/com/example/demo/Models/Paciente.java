package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Paciente {
    private Integer idPaciente;
    private Integer idUsuario;
    private String TipoSangre;
    private String SeguroMedico;


}
