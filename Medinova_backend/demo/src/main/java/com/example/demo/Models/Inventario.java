package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class Inventario {
    private int idItem;
    private String nombre;
    private String tipoItem;
    private String uMedida;
    private int cantidad;
    private String lote;
    private LocalDate fechaVencimiento;
    private String ubicacion;
    private String proveedor;
    private LocalDate fechaIngreso;
    private String observaciones;
    private BigDecimal costo;

    // Getters y setters
}

