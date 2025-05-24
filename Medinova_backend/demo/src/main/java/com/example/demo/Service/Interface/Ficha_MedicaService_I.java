package com.example.demo.Service.Interface;

import com.example.demo.Models.FichaMedica;

import java.util.List;

public interface Ficha_MedicaService_I {
    void insertarFichaMedica(FichaMedica f) throws Exception;
    void actualizarFichaMedica(FichaMedica f) throws Exception;
    void eliminarFichaMedica(Integer idUsuario) throws Exception;
    List<FichaMedica> listarFichasMedicas() throws Exception;
}
