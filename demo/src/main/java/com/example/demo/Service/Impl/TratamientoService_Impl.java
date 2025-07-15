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
        String sql = "call insertar_tratamiento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, t.getTratamiento());
        ps.setInt(2, t.getIdPaciente());
        ps.setInt(3, t.getIdHC());
        ps.setInt(4, t.getIdMedico());
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
    public List<Tratamiento> buscarTratamientosPorIdPaciente(Integer idPaciente) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM tratamiento WHERE id_paciente = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idPaciente);
        ResultSet rs = ps.executeQuery();

        List<Tratamiento> tratamientos = new ArrayList<>();

        while (rs.next()) {
            Tratamiento t = new Tratamiento();
            t.setIdTratamiento(rs.getInt("id_tratamiento"));
            t.setTratamiento(rs.getString("tratamiento"));
            t.setIdPaciente(rs.getInt("id_paciente"));
            t.setIdHC(rs.getInt("id_hc"));
            t.setIdMedico(rs.getInt("id_medico"));
            t.setNombre(rs.getString("nombre"));
            t.setDescripcion(rs.getString("descripcion"));
            t.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            t.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            t.setEstado(rs.getString("estado"));
            t.setArchivo(rs.getBytes("archivo"));
            t.setCosto(rs.getBigDecimal("costo"));

            tratamientos.add(t);
        }

        rs.close();
        ps.close();
        cn.close();

        if (tratamientos.isEmpty()) {
            throw new Exception("No se encontraron tratamientos para el paciente con id: " + idPaciente);
        }

        return tratamientos;
    }



    @Override
    public void actualizarTratamiento(Tratamiento t) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call actualizar_tratamiento(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, t.getIdTratamiento());
        ps.setString(2, t.getTratamiento());
        ps.setInt(3, t.getIdPaciente());
        ps.setInt(4, t.getIdHC());
        ps.setInt(5, t.getIdMedico());
        ps.setString(6, t.getNombre());
        ps.setString(7, t.getDescripcion());
        ps.setDate(8, Date.valueOf(t.getFechaInicio()));
        ps.setDate(9, Date.valueOf(t.getFechaFin()));
        ps.setString(10, t.getEstado());
        ps.setBytes(11, t.getArchivo());
        ps.setBigDecimal(12, t.getCosto());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }

    @Override
    public void eliminarTratamiento(Integer idTratamiento) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call eliminar_tratamiento(?)";
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
            t.setIdMedico(rs.getInt("id_medico"));
            t.setNombre(rs.getString("nombre"));
            t.setDescripcion(rs.getString("descripcion"));
            t.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            t.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            t.setEstado(rs.getString("estado"));
            t.setArchivo(rs.getBytes("archivo"));
            t.setCosto(rs.getBigDecimal("costo"));


            lista.add(t);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }

    @Override
    public List<Tratamiento> buscarTratamientosPorPaciente(int idPaciente) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM tratamiento WHERE id_paciente = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idPaciente);
        ResultSet rs = ps.executeQuery();

        List<Tratamiento> lista = new ArrayList<>();

        while (rs.next()) {
            Tratamiento t = new Tratamiento();
            t.setIdTratamiento(rs.getInt("id_tratamiento"));
            t.setTratamiento(rs.getString("tratamiento"));
            t.setIdPaciente(rs.getInt("id_paciente"));
            t.setIdHC(rs.getInt("id_hc"));
            t.setIdMedico(rs.getInt("id_medico"));
            t.setNombre(rs.getString("nombre"));
            t.setDescripcion(rs.getString("descripcion"));
            t.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            t.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            t.setEstado(rs.getString("estado"));
            t.setArchivo(rs.getBytes("archivo"));
            t.setCosto(rs.getBigDecimal("costo"));

            lista.add(t);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }

}



