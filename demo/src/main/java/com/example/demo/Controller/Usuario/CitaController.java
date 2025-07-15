package com.example.demo.Controller.Usuario;


import com.example.demo.Models.CitaControl;
import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.CitaService_I;
import com.example.demo.Service.Interface.PacienteService_I;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/citas")
public class CitaController {
    private static final Logger logger = LoggerFactory.getLogger(CitaController.class);

    @Autowired
    private CitaService_I citaService;

    @Autowired
    private PacienteService_I pacienteService;

    @PostMapping("/crear")
    public String crearCita(CitaControl c) throws Exception {
        citaService.insertarCitaControl(c);
        return "redirect:/citas";
    }

    @PutMapping("/actualizar")
    @ResponseBody
    public String actualizarCita(@RequestBody CitaControl c) throws Exception {
        citaService.actualizarCitaControl(c);
        return "Cita actualizada correctamente.";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<CitaControl> listarCitas() throws Exception {
        return citaService.listarCitasControl();
    }

    @GetMapping("/tratamiento/{idTratamiento}")
    @ResponseBody
    public ResponseEntity<List<CitaControl>> obtenerCitasPorTratamiento(@PathVariable int idTratamiento) {
        try {
            List<CitaControl> citas = citaService.buscarCitasControlPorTratamiento(idTratamiento);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 📌 Mostrar la vista de citas, con las citas del paciente logueado@GetMapping
    @GetMapping
    public String verPaginaCitas(Model model, HttpSession session) throws Exception {

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            logger.warn("No hay sesión activa, redirigiendo al login.");
            return "redirect:/";
        }

        int idUsuario = usuarioLogueado.getIdUsuario();
        logger.info("Usuario logueado: {}", idUsuario);

        Integer idPaciente = pacienteService.obtenerIdPacientePorUsuario(idUsuario);

        // Si no tiene paciente asociado
        if (idPaciente == null && !usuarioLogueado.getRol().equalsIgnoreCase("recepcionista")
                && !usuarioLogueado.getRol().equalsIgnoreCase("medico")) {
            logger.error("No se encontró paciente para el usuario {}", idUsuario);
            model.addAttribute("error", "No se encontró paciente asociado a este usuario.");
            return "Acceso/login";
        }

        // Listar todas las citas (para recepcionista o médico) o solo las del paciente
        List<CitaControl> citas;

        if (usuarioLogueado.getRol().equalsIgnoreCase("recepcionista")
                || usuarioLogueado.getRol().equalsIgnoreCase("medico")) {
            citas = citaService.listarCitasControl();  // todas
        } else {
            citas = citaService.listarCitasControl()
                    .stream()
                    .filter(c -> c.getIdPaciente() == idPaciente)
                    .collect(Collectors.toList());
        }

        logger.info("Citas encontradas: {}", citas.size());

        model.addAttribute("citas", citas);
        model.addAttribute("usuario", usuarioLogueado);

        // 📌 Cambiar la vista según el rol
        if (usuarioLogueado.getRol().equalsIgnoreCase("recepcionista")
                || usuarioLogueado.getRol().equalsIgnoreCase("medico")) {
            return "Trabajador/citas";   // vista para recepcionista y médico
        } else {
            return "Usuario/citas";      // vista para paciente
        }
    }

}
