package com.example.demo.Service.Interface;

import com.example.demo.Models.HistorialClinico;

import java.util.List;

public interface His_ClinicoService_I {
    void insertarHistorialClinico(HistorialClinico h) throws Exception;
    void actualizarHistorialClinico(HistorialClinico h) throws Exception;
    List<HistorialClinico> buscarHistorialClinicoPorUsuario(int idUsuario) throws Exception;
    List<HistorialClinico> listarHistorialClinico() throws Exception;
}
