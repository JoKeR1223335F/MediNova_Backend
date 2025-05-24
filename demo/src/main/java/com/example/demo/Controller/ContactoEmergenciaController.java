package com.example.demo.Controller;

import com.example.demo.Models.ContactoEmergencia;
import com.example.demo.Service.Interface.Con_EmergenciaService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contactosEmergencia")
@CrossOrigin(origins = "*")
public class ContactoEmergenciaController {

    @Autowired
    private Con_EmergenciaService_I contactoEmergenciaService;

    @PostMapping("/crear")
    public String crearContactoEmergencia(@RequestBody ContactoEmergencia c) throws Exception {
        contactoEmergenciaService.insertarContactoEmergencia(c);
        return "Contacto de emergencia creado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarContactoEmergencia(@RequestBody ContactoEmergencia c) throws Exception {
        contactoEmergenciaService.actualizarContactoEmergencia(c);
        return "Contacto de emergencia actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarContactoEmergencia(@PathVariable Integer id) throws Exception {
        contactoEmergenciaService.eliminarContactoEmergencia(id);
        return "Contacto de emergencia eliminado correctamente.";
    }

    @GetMapping("/listar")
    public List<ContactoEmergencia> listarContactosEmergencia() throws Exception {
        return contactoEmergenciaService.listarContactoEmergencia();
    }
}