package com.example.demo.Controller;

import com.example.demo.Models.HistorialClinico;
import com.example.demo.Service.Interface.His_ClinicoService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/listar")
    public List<HistorialClinico> listarHistorialClinico() throws Exception {
        return historialClinicoService.listarHistorialClinico();
    }



        @GetMapping("/usuario/{idUsuario}")
        public ResponseEntity<List<HistorialClinico>> obtenerHistorialPorUsuario(@PathVariable int idUsuario) {
            try {
                List<HistorialClinico> lista = historialClinicoService.buscarHistorialClinicoPorUsuario(idUsuario);
                if (lista.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(lista, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }



}