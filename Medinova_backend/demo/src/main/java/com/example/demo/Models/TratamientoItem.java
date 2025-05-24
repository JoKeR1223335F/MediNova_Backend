package com.example.demo.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TratamientoItem {
        private Integer idTratamiento;
        private Integer idItem;
        private String dosis;
        private String frecuencia;
        private String duracion;
}
