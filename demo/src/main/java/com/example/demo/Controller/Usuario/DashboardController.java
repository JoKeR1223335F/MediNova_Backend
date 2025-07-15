package com.example.demo.Controller.Usuario;

import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private CitaService_I citaService;

    @Autowired
    private TratamientoService_I tratamientoService;

    @Autowired
    private His_ClinicoService_I hisClinicoService;

    @Autowired
    private PagoService_I pagoService;

    @Autowired
    private Doc_MedicosService_I docMedicosService;

    @Autowired
    private PacienteService_I pacienteService;  // 👈 agregamos servicio de paciente
    @GetMapping({"/home", "/dashboard"})
    public String dashboard(Model model, HttpSession session) throws Exception {

        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null) {
            return "redirect:/";
        }

        int idUsuario = usuarioLogueado.getIdUsuario();

        // Si es recepcionista o médico no busca idPaciente porque no aplica
        Integer idPaciente = null;

        if (usuarioLogueado.getRol().equalsIgnoreCase("paciente")) {
            idPaciente = pacienteService.obtenerIdPacientePorUsuario(idUsuario);

            if (idPaciente == null) {
                model.addAttribute("error", "No se encontró paciente asociado a este usuario.");
                return "Acceso/login";
            }
        }

        var citas = citaService.listarCitasControl();

        // Si es paciente, filtra sus datos específicos, si no, carga todo
        var tratamientos = (usuarioLogueado.getRol().equalsIgnoreCase("paciente"))
                ? tratamientoService.buscarTratamientosPorPaciente(idPaciente)
                : tratamientoService.listarTratamientos();

        var historiales = hisClinicoService.buscarHistorialClinicoPorUsuario(idUsuario);

        var pagos = (usuarioLogueado.getRol().equalsIgnoreCase("paciente"))
                ? pagoService.buscarPagosPorPaciente(idPaciente)
                : pagoService.listarPagos();

        var documentos = docMedicosService.listarDocMedicos();

        var notificaciones = documentos.stream()
                .map(doc -> "Nuevo documento médico: " + doc.getDescripcion() + " - " + doc.getFecha())
                .toList();

        model.addAttribute("citas", citas);
        model.addAttribute("tratamientos", tratamientos);
        model.addAttribute("historiales", historiales);
        model.addAttribute("pagos", pagos);
        model.addAttribute("notificaciones", notificaciones);
        model.addAttribute("usuario", usuarioLogueado);

        // 📌 Según el rol, se devuelve la vista correspondiente
        if (usuarioLogueado.getRol().equalsIgnoreCase("recepcionista")
                || usuarioLogueado.getRol().equalsIgnoreCase("medico")) {
            return "Trabajador/dashboard";
        } else {
            return "Usuario/dashboard";
        }
    }

}
