package com.example.demo.Service.Impl;

import com.example.demo.Models.Paciente;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.PacienteService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class PacienteService_Impl implements PacienteService_I {
        @Override
        public void actualizarPaciente(Paciente p) throws Exception {
            Connection cn = ConexionPostgres.getConexion();
            String sql = "CALL actualizar_paciente(?, ?,?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, p.getIdPaciente());
            ps.setInt(2, p.getIdUsuario());
            ps.setInt(3, p.getIdMedico());
            ps.setString(4, p.getTipoSangre());
            ps.setString(5, p.getSeguroMedico());
            ps.executeUpdate();
            ps.close();
            cn.close();
        }

        @Override
        public List<Paciente> listarPacientes() throws Exception {
            Connection cn = ConexionPostgres.getConexion();
            String sql = "SELECT * FROM listar_pacientes()";
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Paciente> lista = new ArrayList<>();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt("id_paciente"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdMedico(rs.getInt("id_medico"));
                p.setTipoSangre(rs.getString("tipo_sangre"));
                p.setSeguroMedico(rs.getString("seguro_medico"));
                lista.add(p);
            }

            rs.close();
            ps.close();
            cn.close();

            return lista;
        }
        @Override
        public Integer obtenerIdPacientePorUsuario(int idUsuario) throws Exception {
            Connection cn = ConexionPostgres.getConexion();
            String sql = "SELECT id_paciente FROM paciente WHERE id_usuario = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            Integer idPaciente = null;

            if (rs.next()) {
                idPaciente = rs.getInt("id_paciente");
            }

            rs.close();
            ps.close();
            cn.close();

            return idPaciente;  // devuelve null si no se encontró
        }

}


