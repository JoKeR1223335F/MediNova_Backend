package com.example.demo.Service.Impl;

import com.example.demo.Models.Tratamiento;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.TratamientoService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public  class TratamientoService_Impl implements TratamientoService_I {
    @Override
    public void insertarTratamiento(Tratamiento t) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT insertar_tratamiento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, t.getTratamiento());
        ps.setInt(2, t.getIdPaciente());
        ps.setInt(3, t.getIdHC());
        ps.setString(4, t.getNombre());
        ps.setString(5, t.getDescripcion());
        ps.setDate(6, Date.valueOf(t.getFechaInicio()));
        ps.setDate(7, Date.valueOf(t.getFechaFin()));
        ps.setString(8, t.getEstado());
        ps.setBytes(9, t.getArchivo());
        ps.setBigDecimal(10, t.getCosto());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void actualizarTratamiento(Tratamiento t) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT actualizar_tratamiento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, t.getIdTratamiento());
        ps.setString(2, t.getTratamiento());
        ps.setInt(3, t.getIdPaciente());
        ps.setInt(4, t.getIdHC());
        ps.setString(5, t.getNombre());
        ps.setString(6, t.getDescripcion());
        ps.setDate(7, Date.valueOf(t.getFechaInicio()));
        ps.setDate(8, Date.valueOf(t.getFechaFin()));
        ps.setString(9, t.getEstado());
        ps.setBytes(10, t.getArchivo());
        ps.setBigDecimal(11, t.getCosto());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void eliminarTratamiento(Integer idTratamiento) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT eliminar_tratamiento(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idTratamiento);
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public List<Tratamiento> listarTratamientos() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_tratamientos()";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Tratamiento> lista = new ArrayList<>();

        while (rs.next()) {
            Tratamiento t = new Tratamiento();
            t.setIdTratamiento(rs.getInt("id_tratamiento"));
            t.setTratamiento(rs.getString("tratamiento"));
            t.setIdPaciente(rs.getInt("id_paciente"));
            t.setIdHC(rs.getInt("id_hc"));
            t.setNombre(rs.getString("nombre"));
            t.setDescripcion(rs.getString("descripcion"));
            t.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            t.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            t.setEstado(rs.getString("estado"));
            t.setArchivo(rs.getBytes("archivo"));
            t.setCosto(rs.getBigDecimal("costo"));
            t.setFechaEliminacion(rs.getDate("fecha_eliminacion") != null ? rs.getDate("fecha_eliminacion").toLocalDate() : null);
            lista.add(t);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }
}
