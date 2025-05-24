package com.example.demo.Models;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
public class DocMedico {
        private Integer idDoc;
        private Integer idUsuario;
        private Integer idMedico;
        private String tipoDocumento;
        private String descripcion;
        private Timestamp fecha;
        private byte[] archivo;
}
