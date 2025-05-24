package com.example.demo.Controller;
import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.UsuarioService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService_I usuarioService;

    @PostMapping("/crear")
    public String crearUsuario(@RequestBody Usuario u) throws Exception {
        usuarioService.insertarUsuario(u);
        return "Usuario creado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarUsuario(@RequestBody Usuario u) throws Exception {
        usuarioService.actualizarUsuario(u);
        return "Usuario actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) throws Exception {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado correctamente.";
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Integer id) throws Exception {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioService.listarUsuarios();
    }
}