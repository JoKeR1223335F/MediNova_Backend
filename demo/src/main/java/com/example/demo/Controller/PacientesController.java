package com.example.demo.Controller;

import com.example.demo.Models.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacientesController {

    @Autowired
    private com.example.demo.Service.Interface.PacienteService_I pacienteService;



    @PutMapping("/actualizar")
    public String actualizarPaciente(@RequestBody Paciente p) throws Exception {
        pacienteService.actualizarPaciente(p);
        return "Paciente actualizado correctamente.";
    }

    @GetMapping("/listar")
    public List<Paciente> listarPacientes() throws Exception {
        return pacienteService.listarPacientes();
    }
}
