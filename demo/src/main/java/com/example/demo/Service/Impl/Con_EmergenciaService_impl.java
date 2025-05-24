package com.example.demo.Service.Impl;

import com.example.demo.Models.ContactoEmergencia;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.Con_EmergenciaService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public  class Con_EmergenciaService_impl  implements Con_EmergenciaService_I {


    @Override
    public void actualizarContactoEmergencia(ContactoEmergencia c) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call actualizar_contacto_emergencia(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, c.getIdContacto());
        ps.setInt(2, c.getIdUsuario());
        ps.setString(3, c.getNombre());
        ps.setString(4, c.getApellido());
        ps.setString(5, c.getParentesco());
        ps.setString(6, c.getTelefono());
        ps.setString(7, c.getCorreo());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }

    @Override
    public List<ContactoEmergencia> obtenerContactosPorUsuario(Integer idUsuario) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM obtener_contactos_emergencia_por_usuario(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        List<ContactoEmergencia> lista = new ArrayList<>();

        while (rs.next()) {
            ContactoEmergencia c = new ContactoEmergencia();
            c.setIdContacto(rs.getInt("id_contacto"));
            c.setIdUsuario(rs.getInt("id_usuario"));
            c.setNombre(rs.getString("nombre"));
            c.setApellido(rs.getString("apellido"));
            c.setParentesco(rs.getString("parentesco"));
            c.setTelefono(rs.getString("telefono"));
            c.setCorreo(rs.getString("correo"));
            lista.add(c);
        }

        rs.close();
        ps.close();
        cn.close();

        if (lista.isEmpty()) {
            throw new Exception("No se encontraron contactos para el usuario con id: " + idUsuario);
        }

        return lista;
    }



    @Override
    public List<ContactoEmergencia> listarContactoEmergencia() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_contacto_emergencia()";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<ContactoEmergencia> lista = new ArrayList<>();

        while (rs.next()) {
            ContactoEmergencia c = new ContactoEmergencia();
            c.setIdContacto(rs.getInt("id_contacto"));
            c.setIdUsuario(rs.getInt("id_usuario"));
            c.setNombre(rs.getString("nombre"));
            c.setApellido(rs.getString("apellido"));
            c.setParentesco(rs.getString("parentesco"));
            c.setTelefono(rs.getString("telefono"));
            c.setCorreo(rs.getString("correo"));
            lista.add(c);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }
}
