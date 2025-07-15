package com.example.demo.Controller.Usuario;


import com.example.demo.Models.Tratamiento;
import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.TratamientoService_I;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;@Controller
@RequestMapping("/tratamiento")
@CrossOrigin(origins = "*")
public class TratamientoController {

    @Autowired
    private TratamientoService_I tratamientoService;

    // Mostrar la vista tratamiento.html con la lista de tratamientos
    @GetMapping("")
    public String listarTratamientos(Model model, HttpSession session) throws Exception {
        // ✅ Obtener usuario logueado desde sesión
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");
        if (usuarioLogueado == null) {
            return "redirect:/";
        }

        List<Tratamiento> tratamientos = tratamientoService.listarTratamientos();
        model.addAttribute("tratamientos", tratamientos);
        model.addAttribute("usuario", usuarioLogueado);

        // ✅ Retornar vista según rol
        if (usuarioLogueado.getRol().equalsIgnoreCase("recepcionista") ||
                usuarioLogueado.getRol().equalsIgnoreCase("medico")) {
            return "Trabajador/tratamiento";
        } else {
            return "Usuario/tratamiento";
        }
    }

    // Crear tratamiento
    @PostMapping("/crear")
    public String crearTratamiento(Tratamiento t) throws Exception {
        tratamientoService.insertarTratamiento(t);
        return "redirect:/tratamiento";
    }
}

