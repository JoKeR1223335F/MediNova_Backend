package com.example.demo.Service.Impl;

import com.example.demo.Models.Inventario;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.InventarioService_I;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class InventarioService_Impl implements InventarioService_I {
    @Override
    public void insertarInventario(Inventario i) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call insertar_inventario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setString(1, i.getNombre());
        ps.setString(2, i.getTipoItem());
        ps.setString(3, i.getUMedida());
        ps.setInt(4, i.getCantidad());
        ps.setString(5, i.getLote());
        ps.setDate(6, Date.valueOf(i.getFechaVencimiento()));
        ps.setString(7, i.getUbicacion());
        ps.setString(8, i.getProveedor());
        ps.setDate(9, Date.valueOf(i.getFechaIngreso()));
        ps.setString(10, i.getObservaciones());
        ps.setBigDecimal(11, i.getCosto());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void actualizarInventario(Inventario i) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call actualizar_inventario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, i.getIdItem());
        ps.setString(2, i.getNombre());
        ps.setString(3, i.getTipoItem());
        ps.setString(4, i.getUMedida());
        ps.setInt(5, i.getCantidad());
        ps.setString(6, i.getLote());
        ps.setDate(7, Date.valueOf(i.getFechaVencimiento()));
        ps.setString(8, i.getUbicacion());
        ps.setString(9, i.getProveedor());
        ps.setDate(10, Date.valueOf(i.getFechaIngreso()));
        ps.setString(11, i.getObservaciones());
        ps.setBigDecimal(12, i.getCosto());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public void eliminarInventario(int idItem) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call eliminar_inventario(?)";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idItem);
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public List<Inventario> listarInventario() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "select * from inventario";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Inventario> lista = new ArrayList<>();

        while (rs.next()) {
            Inventario i = new Inventario();
            i.setIdItem(rs.getInt("id_item"));
            i.setNombre(rs.getString("nombre"));
            i.setTipoItem(rs.getString("tipo_item"));
            i.setUMedida(rs.getString("u_medida"));
            i.setCantidad(rs.getInt("cantidad"));
            i.setLote(rs.getString("lote"));
            i.setFechaVencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
            i.setUbicacion(rs.getString("ubicacion"));
            i.setProveedor(rs.getString("proveedor"));
            i.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
            i.setObservaciones(rs.getString("observaciones"));
            i.setCosto(rs.getBigDecimal("costo"));
            lista.add(i);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }

    @Override
    public Inventario buscarInventarioPorId(int idItem) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM inventario WHERE id_item = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idItem);
        ResultSet rs = ps.executeQuery();

        Inventario i = null;

        if (rs.next()) {
            i = new Inventario();
            i.setIdItem(rs.getInt("id_item"));
            i.setNombre(rs.getString("nombre"));
            i.setTipoItem(rs.getString("tipo_item"));
            i.setUMedida(rs.getString("u_medida"));
            i.setCantidad(rs.getInt("cantidad"));
            i.setLote(rs.getString("lote"));
            i.setFechaVencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
            i.setUbicacion(rs.getString("ubicacion"));
            i.setProveedor(rs.getString("proveedor"));
            i.setFechaIngreso(rs.getDate("fecha_ingreso").toLocalDate());
            i.setObservaciones(rs.getString("observaciones"));
            i.setCosto(rs.getBigDecimal("costo"));
        }

        rs.close();
        ps.close();
        cn.close();

        return i;
    }

}
