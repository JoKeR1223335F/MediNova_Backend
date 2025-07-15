package com.example.demo.Controller.Acceso;


import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.UsuarioService_I;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("")
public class RegisterController {

    @Autowired
    private UsuarioService_I usuarioService;

    // GET: mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Acceso/registro";
    }

    // POST: registrar usuario
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario,
                                   RedirectAttributes redirectAttrs,
                                   HttpSession session) {
        try {
            // Guarda usuario en BD
            usuarioService.insertarUsuario(usuario);

            // Guarda usuario en sesión
            session.setAttribute("usuarioLogueado", usuario);

            redirectAttrs.addFlashAttribute("success", "Usuario registrado correctamente");
            return "redirect:/dashboard";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("error", "Error al registrar usuario");
            return "redirect:/registro";
        }
    }
}
