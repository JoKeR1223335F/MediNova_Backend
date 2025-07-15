package com.example.demo.Controller.Usuario;

import com.example.demo.Models.CitaControl;
import com.example.demo.Models.Tratamiento;
import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.CitaService_I;
import com.example.demo.Service.Interface.His_ClinicoService_I;
import com.example.demo.Service.Interface.PacienteService_I;
import com.example.demo.Service.Interface.TratamientoService_I;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private His_ClinicoService_I his_ClinicoService_I;

    @Autowired
    private CitaService_I citaService_I;

    @Autowired
    private TratamientoService_I tratamientoService_I;

    @Autowired
    private PacienteService_I pacienteService_I;
    @GetMapping("")
    public String mostrarPerfil(@RequestParam(name = "idTratamiento", required = false) Integer idTratamiento,
                                HttpSession session, Model model) throws Exception {
        // 1️⃣ Validar sesión
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/";
        }

        int id_usuario = usuarioLogueado.getIdUsuario();

        // 2️⃣ Si es trabajador, no buscar id_paciente ni tratamientos
        if (usuarioLogueado.getRol().equalsIgnoreCase("recepcionista")
                || usuarioLogueado.getRol().equalsIgnoreCase("medico")) {

            model.addAttribute("usuario", usuarioLogueado);
            // Agregar historial médico del usuario (si aplica)
            model.addAttribute("historial_medico", his_ClinicoService_I.buscarHistorialClinicoPorUsuario(id_usuario));

            return "Trabajador/perfil";
        }

        // 3️⃣ Si es paciente, buscar id_paciente
        Integer id_paciente = pacienteService_I.obtenerIdPacientePorUsuario(id_usuario);
        if (id_paciente == null) {
            model.addAttribute("error", "No se encontró paciente asociado a este usuario.");
            return "ERRORS/error";
        }

        // 4️⃣ Obtener tratamientos
        List<Tratamiento> tratamientos;
        try {
            tratamientos = tratamientoService_I.buscarTratamientosPorIdPaciente(id_paciente);
        } catch (Exception e) {
            tratamientos = new ArrayList<>();
        }

        // 5️⃣ Agregar datos al modelo
        model.addAttribute("usuario", usuarioLogueado);
        model.addAttribute("historial_medico", his_ClinicoService_I.buscarHistorialClinicoPorUsuario(id_usuario));
        model.addAttribute("tratamientos", tratamientos);

        // 6️⃣ Si se envió un tratamiento, cargar sus citas
        if (idTratamiento != null) {
            List<CitaControl> citas = citaService_I.buscarCitasControlPorTratamiento(idTratamiento);
            model.addAttribute("citas", citas);
            model.addAttribute("idTratamientoSeleccionado", idTratamiento);
        }

        // 7️⃣ Retornar vista para paciente
        return "Usuario/perfil";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
