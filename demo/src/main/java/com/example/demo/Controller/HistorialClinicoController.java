package com.example.demo.Controller;

import com.example.demo.Models.HistorialClinico;
import com.example.demo.Service.Interface.His_ClinicoService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historialClinico")
@CrossOrigin(origins = "*")
public class HistorialClinicoController {

    @Autowired
    private His_ClinicoService_I historialClinicoService;

    @PostMapping("/crear")
    public String crearHistorialClinico(@RequestBody HistorialClinico h) throws Exception {
        historialClinicoService.insertarHistorialClinico(h);
        return "Historial clínico registrado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarHistorialClinico(@RequestBody HistorialClinico h) throws Exception {
        historialClinicoService.actualizarHistorialClinico(h);
        return "Historial clínico actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{idHC}")
    public String eliminarHistorialClinico(@PathVariable Integer idHC) throws Exception {
        historialClinicoService.eliminarHistorialClinico(idHC);
        return "Historial clínico eliminado correctamente.";
    }

    @GetMapping("/listar")
    public List<HistorialClinico> listarHistorialClinico() throws Exception {
        return historialClinicoService.listarHistorialClinico();
    }
}