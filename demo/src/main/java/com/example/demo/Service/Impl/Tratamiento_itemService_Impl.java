package com.example.demo.Service.Impl;

import com.example.demo.Models.Tratamiento;
import com.example.demo.Models.TratamientoItem;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.Tratamiento_itemService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class Tratamiento_itemService_Impl implements Tratamiento_itemService_I {
    @Override
    public void insertarTratamientoItem(TratamientoItem ti) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call insertar_tratamiento_item(?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, ti.getIdTratamiento());
        ps.setInt(2, ti.getIdItem());
        ps.setString(3, ti.getDosis());
        ps.setString(4, ti.getFrecuencia());
        ps.setString(5, ti.getDuracion());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void actualizarTratamientoItem(TratamientoItem ti) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call actualizar_tratamiento_item(?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, ti.getIdTratamiento());
        ps.setInt(2, ti.getIdItem());
        ps.setString(3, ti.getDosis());
        ps.setString(4, ti.getFrecuencia());
        ps.setString(5, ti.getDuracion());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void eliminarTratamientoItem(Integer idTratamiento, Integer idItem) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call eliminar_tratamiento_item(?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idTratamiento);
        ps.setInt(2, idItem);
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public List<TratamientoItem> listarTratamientoItems(Integer idTratamiento) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "select * from listar_tratamiento_items(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idTratamiento);
        ResultSet rs = ps.executeQuery();

        List<TratamientoItem> lista = new ArrayList<>();

        while (rs.next()) {
            TratamientoItem ti = new TratamientoItem();
            ti.setIdTratamiento(rs.getInt("id_tratamiento"));
            ti.setIdItem(rs.getInt("id_item"));
            ti.setDosis(rs.getString("dosis"));
            ti.setFrecuencia(rs.getString("frecuencia"));
            ti.setDuracion(rs.getString("duracion"));
            lista.add(ti);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }

    }


