package com.example.demo.Controller;

import com.example.demo.Models.CitaControl;
import com.example.demo.Service.Interface.CitaService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/citas")

@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService_I citaService;

    @PostMapping("/crear")
    public String crearCita(@RequestBody CitaControl c) throws Exception {
        citaService.insertarCitaControl(c);
        return "Cita creada correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarCita(@RequestBody CitaControl c) throws Exception {
        citaService.actualizarCitaControl(c);
        return "Cita actualizada correctamente.";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarCita(@PathVariable int id) throws Exception {
        citaService.eliminarCitaControl(id);
        return "Cita eliminada correctamente.";
    }

    @GetMapping("/listar")
    public List<CitaControl> listarCitas() throws Exception {
        return citaService.listarCitasControl();
    }
}