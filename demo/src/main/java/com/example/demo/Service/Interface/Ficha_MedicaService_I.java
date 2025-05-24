package com.example.demo.Service.Interface;

import com.example.demo.Models.FichaMedica;

import java.util.List;

public interface Ficha_MedicaService_I {
    FichaMedica buscarFichaMedicaPorUsuario(int idUsuario) throws Exception;
    void actualizarFichaMedica(FichaMedica f) throws Exception;
    List<FichaMedica> listarFichasMedicas() throws Exception;
}
