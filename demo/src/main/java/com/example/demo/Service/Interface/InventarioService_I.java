package com.example.demo.Service.Interface;

import com.example.demo.Models.Inventario;

import java.util.List;

public interface InventarioService_I {
    void insertarInventario(Inventario i) throws Exception;
    void actualizarInventario(Inventario i) throws Exception;
    void eliminarInventario(int idItem) throws Exception;
    List<Inventario> listarInventario() throws Exception;
    Inventario buscarInventarioPorId(int idItem) throws Exception;
}
