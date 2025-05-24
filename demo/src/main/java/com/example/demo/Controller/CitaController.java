package com.example.demo.Controller;

import com.example.demo.Models.CitaControl;
import com.example.demo.Service.Interface.CitaService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/listar")
    public List<CitaControl> listarCitas() throws Exception {
        return citaService.listarCitasControl();
    }
    @GetMapping("/tratamiento/{idTratamiento}")
    public ResponseEntity<List<CitaControl>> obtenerCitasPorTratamiento(@PathVariable int idTratamiento) {
        try {
            List<CitaControl> citas = citaService.buscarCitasControlPorTratamiento(idTratamiento);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}