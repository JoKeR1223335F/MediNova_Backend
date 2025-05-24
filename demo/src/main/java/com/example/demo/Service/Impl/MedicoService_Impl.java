package com.example.demo.Service.Impl;

import com.example.demo.Models.Medico;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.MedicoService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public  class MedicoService_Impl implements MedicoService_I {

    @Override
    public void actualizarMedico(Medico m) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "Call actualizar_medico(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, m.getIdMedico());
        ps.setInt(2, m.getIdUsuario());
        ps.setString(3, m.getEspecialidad());
        ps.setString(4, m.getN_colegiatura());
        ps.setString(5, m.getFoto());
        ps.setString(6, m.getObservaciones());
        ps.setBigDecimal(7, m.getRemuneracion());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void eliminarMedico(Integer idMedico) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "DELETE FROM medico WHERE id_medico = ?;\n";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idMedico);
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public List<Medico> listarMedicos() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_medicos()";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Medico> lista = new ArrayList<>();

        while (rs.next()) {
            Medico m = new Medico();
            m.setIdMedico(rs.getInt("id_medico"));
            m.setIdUsuario(rs.getInt("id_usuario"));
            m.setEspecialidad(rs.getString("especialidad"));
            m.setN_colegiatura(rs.getString("n_colegiatura"));
            m.setFoto(rs.getString("foto"));
            m.setObservaciones(rs.getString("observaciones"));
            m.setRemuneracion(rs.getBigDecimal("remuneracion"));
            lista.add(m);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }
}
