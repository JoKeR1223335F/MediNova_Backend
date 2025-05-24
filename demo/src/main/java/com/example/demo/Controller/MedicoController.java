package com.example.demo.Controller;
import com.example.demo.Models.Medico;
import com.example.demo.Service.Interface.MedicoService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    @Autowired
    private MedicoService_I medicoService;

    // Listar todos los médicos
    @GetMapping("/listar")
    public List<Medico> listarMedicos() throws Exception {
        return medicoService.listarMedicos();
    }
    // Actualizar médico
    @PutMapping("/actualizar")
    public String actualizarMedico(@RequestBody Medico medico) throws Exception {
        medicoService.actualizarMedico(medico);
        return "Médico actualizado correctamente.";
    }

    // Eliminar médico
    @DeleteMapping("/{idMedico}")
    public String eliminarMedico(@PathVariable Integer idMedico) throws Exception {
        medicoService.eliminarMedico(idMedico);
        return "Médico eliminado correctamente.";
    }
}