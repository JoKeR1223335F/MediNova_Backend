package com.example.demo.Controller.Usuario;

import com.example.demo.Models.HistorialClinico;
import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.His_ClinicoService_I;
import com.example.demo.Service.Interface.UsuarioService_I;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/historialClinico")
@CrossOrigin(origins = "*")
public class HistorialClinicoController {

    @Autowired
    private His_ClinicoService_I historialClinicoService;

    @Autowired
    private UsuarioService_I usuarioService;

    /**
     * 📄 Mostrar página de historial clínico del usuario logueado (desde sesión)
     */
    @GetMapping("")
    public String mostrarHistorialClinico(Model model, HttpSession session) {
        try {
            // 1️⃣ Obtener usuario logueado desde sesión
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuarioLogueado == null) {
                return "redirect:/"; // No hay sesión, volver a login
            }

            int idUsuario = usuarioLogueado.getIdUsuario();

            // 2️⃣ Buscar historial clínico de ese usuario
            List<HistorialClinico> historial = historialClinicoService.buscarHistorialClinicoPorUsuario(idUsuario);

            // 3️⃣ Agregar datos al modelo
            model.addAttribute("consultas", historial);
            model.addAttribute("paciente", usuarioLogueado);
            model.addAttribute("consultas", null);
            model.addAttribute("examenes", null);
            model.addAttribute("documentos", null);

            // 📌 Retornar vista según rol
            if (usuarioLogueado.getRol().equalsIgnoreCase("recepcionista")
                    || usuarioLogueado.getRol().equalsIgnoreCase("medico")) {
                return "Trabajador/hClinico";
            } else {
                return "Usuario/hClinico";
            }

        } catch (Exception e) {
            model.addAttribute("error", "No se pudo cargar el historial clínico.");
            return "ERRORS/error";
        }
    }

    /**
     * ➕ Crear historial clínico desde formulario (para usuario logueado)
     */
    @PostMapping("/crear")
    public String crearHistorialClinico(@ModelAttribute HistorialClinico historialClinico, HttpSession session, Model model) {
        try {
            // Obtener usuario logueado
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuarioLogueado == null) {
                return "redirect:/";
            }

            historialClinico.setIdUsuario(usuarioLogueado.getIdUsuario());

            historialClinicoService.insertarHistorialClinico(historialClinico);
            return "redirect:/historialclinico";

        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar historial clínico.");
            return "error";
        }
    }

    /**
     * 🔄 Actualizar historial clínico (usuario logueado)
     */
    @PostMapping("/actualizar")
    public String actualizarHistorialClinico(@ModelAttribute HistorialClinico historialClinico, HttpSession session, Model model) {
        try {
            // Obtener usuario logueado
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuarioLogueado == null) {
                return "redirect:/";
            }

            historialClinico.setIdUsuario(usuarioLogueado.getIdUsuario());

            historialClinicoService.actualizarHistorialClinico(historialClinico);
            return "redirect:/historialclinico";

        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar historial clínico.");
            return "error";
        }
    }

    /**
     * 📃 API REST — Listar todos los historiales clínicos
     */
    @GetMapping("/listar")
    @ResponseBody
    public List<HistorialClinico> listarHistorialClinico() throws Exception {
        return historialClinicoService.listarHistorialClinico();
    }

    /**
     * 📃 API REST — Listar historiales clínicos del usuario logueado
     */
    @GetMapping("/mios")
    @ResponseBody
    public ResponseEntity<List<HistorialClinico>> obtenerHistorialMio(HttpSession session) {
        try {
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuarioLogueado == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            List<HistorialClinico> lista = historialClinicoService.buscarHistorialClinicoPorUsuario(usuarioLogueado.getIdUsuario());
            if (lista.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}