package com.example.demo.Controller;

import com.example.demo.Models.FichaMedica;
import com.example.demo.Service.Interface.Ficha_MedicaService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fichasMedicas")
@CrossOrigin(origins = "*")
public class FichaMedicaController {

    @Autowired
    private Ficha_MedicaService_I fichaMedicaService;

    @PutMapping("/actualizar")
    public String actualizarFichaMedica(@RequestBody FichaMedica f) throws Exception {
        fichaMedicaService.actualizarFichaMedica(f);
        return "Ficha médica actualizada correctamente.";
    }
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<FichaMedica> obtenerFichaMedicaPorUsuario(@PathVariable int idUsuario) {
        try {
            FichaMedica ficha = fichaMedicaService.buscarFichaMedicaPorUsuario(idUsuario);
            if (ficha != null) {
                return ResponseEntity.ok(ficha);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();  // Para que loggee bien en consola qué está fallando
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/listar")
    public List<FichaMedica> listarFichasMedicas() throws Exception {
        return fichaMedicaService.listarFichasMedicas();
    }
}