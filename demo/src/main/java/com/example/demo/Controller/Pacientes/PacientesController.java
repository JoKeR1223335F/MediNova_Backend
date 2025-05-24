package com.example.demo.Controller.Pacientes;

import com.example.demo.Models.Paciente;
import com.example.demo.Service.Interface.PacienteService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacientesController {

    @Autowired
    private com.example.demo.Service.Interface.PacienteService_I pacienteService;

    @PostMapping("/crear")
    public String crearPaciente(@RequestBody Paciente p) throws Exception {
        pacienteService.insertarPaciente(p);
        return "Paciente creado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarPaciente(@RequestBody Paciente p) throws Exception {
        pacienteService.actualizarPaciente(p);
        return "Paciente actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Integer id) throws Exception {
        pacienteService.eliminarPaciente(id);
        return "Paciente eliminado correctamente.";
    }

    @GetMapping("/listar")
    public List<Paciente> listarPacientes() throws Exception {
        return pacienteService.listarPacientes();
    }
}
