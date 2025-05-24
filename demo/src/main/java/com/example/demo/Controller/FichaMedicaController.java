package com.example.demo.Controller;

import com.example.demo.Models.FichaMedica;
import com.example.demo.Service.Interface.Ficha_MedicaService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fichasMedicas")
@CrossOrigin(origins = "*")
public class FichaMedicaController {

    @Autowired
    private Ficha_MedicaService_I fichaMedicaService;

    @PostMapping("/crear")
    public String crearFichaMedica(@RequestBody FichaMedica f) throws Exception {
        fichaMedicaService.insertarFichaMedica(f);
        return "Ficha médica registrada correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarFichaMedica(@RequestBody FichaMedica f) throws Exception {
        fichaMedicaService.actualizarFichaMedica(f);
        return "Ficha médica actualizada correctamente.";
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public String eliminarFichaMedica(@PathVariable Integer idUsuario) throws Exception {
        fichaMedicaService.eliminarFichaMedica(idUsuario);
        return "Ficha médica eliminada correctamente.";
    }

    @GetMapping("/listar")
    public List<FichaMedica> listarFichasMedicas() throws Exception {
        return fichaMedicaService.listarFichasMedicas();
    }
}