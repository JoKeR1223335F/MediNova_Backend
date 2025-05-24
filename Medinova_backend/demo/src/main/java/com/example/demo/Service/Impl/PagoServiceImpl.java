package com.example.demo.Service.Impl;

import com.example.demo.Models.Pago;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.PagoService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class PagoServiceImpl implements PagoService_I {
    @Override
    public void insertarPago(Pago p) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT insertar_pago(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, p.getIdPaciente());
        ps.setString(2, p.getConcepto());
        ps.setBigDecimal(3, p.getMonto());
        ps.setDate(4, Date.valueOf(p.getFechaPago()));
        ps.setString(5, p.getMetodoPago());
        ps.setString(6, p.getEstado());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void actualizarPago(Pago p) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT actualizar_pago(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, p.getIdPago());
        ps.setInt(2, p.getIdPaciente());
        ps.setString(3, p.getConcepto());
        ps.setBigDecimal(4, p.getMonto());
        ps.setDate(5, Date.valueOf(p.getFechaPago()));
        ps.setString(6, p.getMetodoPago());
        ps.setString(7, p.getEstado());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void eliminarPago(int idPago) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT eliminar_pago(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idPago);
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public List<Pago> listarPagos() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_pagos()";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Pago> lista = new ArrayList<>();

        while (rs.next()) {
            Pago p = new Pago();
            p.setIdPago(rs.getInt("id_pago"));
            p.setIdPaciente(rs.getInt("id_paciente"));
            p.setConcepto(rs.getString("concepto"));
            p.setMonto(rs.getBigDecimal("monto"));
            p.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
            p.setMetodoPago(rs.getString("metodo_pago"));
            p.setEstado(rs.getString("estado"));
            lista.add(p);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }
}
