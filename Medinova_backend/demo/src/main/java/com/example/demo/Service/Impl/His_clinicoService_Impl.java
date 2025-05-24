package com.example.demo.Service.Impl;

import com.example.demo.Models.HistorialClinico;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.His_ClinicoService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class His_clinicoService_Impl implements His_ClinicoService_I {
    @Override
    public void insertarHistorialClinico(HistorialClinico h) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT insertar_historial_clinico(?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, h.getIdUsuario());
        ps.setString(2, h.getObservaciones());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void actualizarHistorialClinico(HistorialClinico h) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT actualizar_historial_clinico(?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, h.getIdHC());
        ps.setInt(2, h.getIdUsuario());
        ps.setString(3, h.getObservaciones());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void eliminarHistorialClinico(Integer idHC) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT eliminar_historial_clinico(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idHC);
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public List<HistorialClinico> listarHistorialClinico() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_historial_clinico()";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<HistorialClinico> lista = new ArrayList<>();

        while (rs.next()) {
            HistorialClinico h = new HistorialClinico();
            h.setIdHC(rs.getInt("id_HC"));
            h.setIdUsuario(rs.getInt("id_usuario"));
            h.setFecha(rs.getTimestamp("fecha"));
            h.setObservaciones(rs.getString("observaciones"));
            lista.add(h);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }
}
