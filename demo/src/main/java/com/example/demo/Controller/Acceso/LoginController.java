package com.example.demo.Controller.Acceso;

import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.UsuarioService_I;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@CrossOrigin(origins = "*")
public class LoginController {

    private final UsuarioService_I usuarioService;

    public LoginController(UsuarioService_I usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping("/")
    public String login() {
        return "Acceso/login";
    }

    @PostMapping("/validarLogin")
    public String validarLogin(@RequestParam String usuarioOCorreo,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        Usuario usuario = usuarioService.validarCredenciales(usuarioOCorreo, password);

        if (usuario != null) {
            // Guarda el usuario en sesión
            session.setAttribute("usuarioLogueado", usuario);

            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "Acceso/login";
        }
    }
}
