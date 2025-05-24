package com.example.demo.Controller;


import com.example.demo.Models.Tratamiento;
import com.example.demo.Service.Interface.TratamientoService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
@CrossOrigin(origins = "*")
public class TratamientoController {

    @Autowired
    private TratamientoService_I tratamientoService;

    @PostMapping("/crear")
    public String crearTratamiento(@RequestBody Tratamiento t) throws Exception {
        tratamientoService.insertarTratamiento(t);
        return "Tratamiento registrado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarTratamiento(@RequestBody Tratamiento t) throws Exception {
        tratamientoService.actualizarTratamiento(t);
        return "Tratamiento actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{idTratamiento}")
    public String eliminarTratamiento(@PathVariable Integer idTratamiento) throws Exception {
        tratamientoService.eliminarTratamiento(idTratamiento);
        return "Tratamiento eliminado correctamente.";
    }

    @GetMapping("/listar")
    public List<Tratamiento> listarTratamientos() throws Exception {
        return tratamientoService.listarTratamientos();
    }
}
