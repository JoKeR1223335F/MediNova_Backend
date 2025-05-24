package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class FichaMedica {
    private Integer idUsuario;
    private String grupoSanguineo;
    private String factorRh;
    private String alergias;
    private String enfermedadesCronicas;
    private String medicacionPermanente;
    private String discapacidades;
    private BigDecimal peso;
    private BigDecimal estatura;
    private String antecedentesQuirurgicos;
    private String tipoSeguro;
    private String nroHistoriaClinica;

}
