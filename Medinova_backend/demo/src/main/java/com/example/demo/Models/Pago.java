package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Pago {
    private int idPago;
    private int idPaciente;
    private String concepto;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private String metodoPago;
    private String estado;

    // Getters y setters
}

