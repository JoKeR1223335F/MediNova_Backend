package com.example.demo.Service.Impl;

import com.example.demo.Models.DocMedico;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.Doc_MedicosService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public  class Doc_MedicosService_Impl implements Doc_MedicosService_I {
    @Override
    public void insertarDocMedico(DocMedico d) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call insertar_doc_medico(?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, d.getIdUsuario());
        ps.setInt(2, d.getIdMedico());
        ps.setString(3, d.getTipoDocumento());
        ps.setString(4, d.getDescripcion());
        ps.setBytes(5, d.getArchivo());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void actualizarDocMedico(DocMedico d) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call actualizar_doc_medico(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, d.getIdDoc());
        ps.setInt(2, d.getIdUsuario());
        ps.setInt(3, d.getIdMedico());
        ps.setString(4, d.getTipoDocumento());
        ps.setString(5, d.getDescripcion());
        ps.setBytes(6, d.getArchivo());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }

    @Override
    public List<DocMedico> listarDocMedicos() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_doc_medicos()";
        PreparedStatement ps = cn.prepareStatement(sql);

        List<DocMedico> lista = new ArrayList<>();

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            DocMedico d = new DocMedico();
            d.setIdDoc(rs.getInt("id_doc"));
            d.setIdUsuario(rs.getInt("id_usuario"));
            d.setIdMedico(rs.getInt("id_medico"));
            d.setTipoDocumento(rs.getString("tipo_documento"));
            d.setDescripcion(rs.getString("descripcion"));
            d.setFecha(rs.getTimestamp("fecha"));
            d.setArchivo(rs.getBytes("archivo"));
            lista.add(d);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }


    @Override
    public List<DocMedico> buscarDocMedicosPorUsuario(int idUsuario) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM buscar_doc_medicos_por_usuario(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        List<DocMedico> lista = new ArrayList<>();

        while (rs.next()) {
            DocMedico d = new DocMedico();
            d.setIdDoc(rs.getInt("id_doc"));
            d.setIdUsuario(rs.getInt("id_usuario"));
            d.setIdMedico(rs.getInt("id_medico"));
            d.setTipoDocumento(rs.getString("tipo_documento"));
            d.setDescripcion(rs.getString("descripcion"));
            d.setFecha(rs.getTimestamp("fecha"));
            d.setArchivo(rs.getBytes("archivo"));
            lista.add(d);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }


}
