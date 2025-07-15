package com.example.demo.Service.Impl;

import com.example.demo.Models.Usuario;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.UsuarioService_I;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService_Impl implements UsuarioService_I {

    // INSERTAR USUARIO
    @Override
    public void insertarUsuario(Usuario u) throws SQLException {
        String sql = "call insertar_usuario(?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = ConexionPostgres.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, "paciente");
            ps.setString(4, u.getNombre());
            ps.setString(5, u.getApellido());
            ps.setString(6, u.getDni());
            ps.setString(7, u.getDireccion());
            ps.setDate(8, Date.valueOf(u.getFechaNacimiento()));
            ps.setString(9, u.getSexo());
            ps.setString(10, u.getCorreo());
            ps.setString(11, u.getTelefono());

            ps.executeUpdate();
        }
    }

    // ACTUALIZAR USUARIO
    @Override
    public void actualizarUsuario(Usuario u) throws SQLException {
        String sql = "UPDATE usuario SET username=?, password=?, rol=?, nombre=?, apellido=?, dni=?, direccion=?, fecha_nacimiento=?, sexo=?, correo=?, telefono=?, estado=? WHERE id_usuario=?";
        try (Connection conn = ConexionPostgres.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRol());
            ps.setString(4, u.getNombre());
            ps.setString(5, u.getApellido());
            ps.setString(6, u.getDni());
            ps.setString(7, u.getDireccion());
            ps.setDate(8, Date.valueOf(u.getFechaNacimiento()));
            ps.setString(9, u.getSexo());
            ps.setString(10, u.getCorreo());
            ps.setString(11, u.getTelefono());
            ps.setBoolean(12, u.isEstado());
            ps.setInt(13, u.getIdUsuario());

            ps.executeUpdate();
        }
    }

    // ELIMINADO LÓGICO
    @Override
    public void eliminarUsuario(Integer idUsuario) throws SQLException {
        String sql = "UPDATE usuario SET estado=false WHERE id_usuario=?";
        try (Connection conn = ConexionPostgres.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        }
    }

    // BUSCAR POR ID
    @Override
    public Usuario obtenerUsuarioPorId(Integer idUsuario) throws SQLException {
        try (Connection conn = ConexionPostgres.getConexion()) {
            return obtenerUsuarioPorId(idUsuario, conn);
        }
    }

    // BUSCAR POR ID (versión con conexión abierta)
    private Usuario obtenerUsuarioPorId(Integer idUsuario, Connection conn) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id_usuario=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = mapearUsuario(rs);
                }
            }
        }
        return usuario;
    }

    // LISTAR TODOS
    @Override
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE estado != false";

        try (Connection conn = ConexionPostgres.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }
        return lista;
    }

    // VALIDAR CREDENCIALES
    @Override
    public Usuario validarCredenciales(String usuarioOCorreo, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM validar_usuario_credencial(?, ?)";

        try (Connection conn = ConexionPostgres.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuarioOCorreo);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getBoolean("valido")) {
                    int id = rs.getInt("id_usuario");
                    usuario = obtenerUsuarioPorId(id, conn);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    // MAPEADOR DE RESULTSET A OBJETO
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setRol(rs.getString("rol"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setDni(rs.getString("dni"));
        u.setDireccion(rs.getString("direccion"));

        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
        u.setFechaNacimiento(fechaNacimiento != null ? fechaNacimiento.toLocalDate() : null);

        u.setSexo(rs.getString("sexo"));
        u.setCorreo(rs.getString("correo"));
        u.setTelefono(rs.getString("telefono"));
        u.setEstado(rs.getBoolean("estado"));
        return u;
    }
}
