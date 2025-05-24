package com.example.demo.Service.Impl;

import com.example.demo.Models.CitaControl;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.CitaService_I;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitaService_Impl implements CitaService_I {
    @Override
    public void insertarCitaControl(CitaControl c) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call insertar_cita_control(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, c.getIdPaciente());
        ps.setInt(2, c.getIdTratamiento());
        ps.setDate(3, Date.valueOf(c.getFecha()));
        ps.setTime(4, Time.valueOf(c.getHora()));
        ps.setString(5, c.getMotivo());
        ps.setString(6, c.getObservaciones());
        ps.setString(7, c.getEstado());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void actualizarCitaControl(CitaControl c) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call actualizar_cita_control(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, c.getIdCita());
        ps.setInt(2, c.getIdPaciente());
        ps.setInt(3, c.getIdTratamiento());
        ps.setDate(4, Date.valueOf(c.getFecha()));
        ps.setTime(5, Time.valueOf(c.getHora()));
        ps.setString(6, c.getMotivo());
        ps.setString(7, c.getObservaciones());
        ps.setString(8, c.getEstado());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }

    @Override
    public List<CitaControl> listarCitasControl() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_citas_control()";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<CitaControl> lista = new ArrayList<>();

        while (rs.next()) {
            CitaControl c = new CitaControl();
            c.setIdCita(rs.getInt("id_cita"));
            c.setIdPaciente(rs.getInt("id_paciente"));
            c.setIdTratamiento(rs.getInt("id_tratamiento"));
            c.setFecha(rs.getDate("fecha").toLocalDate());
            c.setHora(rs.getTime("hora").toLocalTime());
            c.setMotivo(rs.getString("motivo"));
            c.setObservaciones(rs.getString("observaciones"));
            c.setEstado(rs.getString("estado"));
            lista.add(c);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }

    @Override
    public List<CitaControl> buscarCitasControlPorTratamiento(int idTratamiento) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM buscar_citas_control_por_tratamiento(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idTratamiento);
        ResultSet rs = ps.executeQuery();

        List<CitaControl> lista = new ArrayList<>();

        while (rs.next()) {
            CitaControl c = new CitaControl();
            c.setIdCita(rs.getInt("id_cita"));
            c.setIdPaciente(rs.getInt("id_paciente"));
            c.setIdTratamiento(rs.getInt("id_tratamiento"));
            c.setFecha(rs.getDate("fecha").toLocalDate());
            c.setHora(rs.getTime("hora").toLocalTime());
            c.setMotivo(rs.getString("motivo"));
            c.setObservaciones(rs.getString("observaciones"));
            c.setEstado(rs.getString("estado"));
            lista.add(c);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }

}
