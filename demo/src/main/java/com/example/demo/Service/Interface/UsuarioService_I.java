package com.example.demo.Service.Interface;

import com.example.demo.Models.Paciente;
import com.example.demo.Models.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioService_I {
    void  insertarUsuario(Usuario u) throws Exception;
    void  actualizarUsuario(Usuario u) throws Exception;
    void  eliminarUsuario(Integer idUsuario) throws Exception;
    Usuario obtenerUsuarioPorId(Integer idUsuario) throws SQLException;
    List<Usuario> listarUsuarios() throws Exception;
    Usuario validarCredenciales(String usuarioOCorreo, String password);
}
