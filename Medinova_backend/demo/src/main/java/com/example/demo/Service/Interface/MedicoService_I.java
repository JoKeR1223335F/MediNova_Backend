package com.example.demo.Service.Interface;

import com.example.demo.Models.Medico;

import java.util.List;

public interface MedicoService_I {
    void insertarMedico(Medico m) throws Exception;
    void actualizarMedico(Medico m) throws Exception;
    void eliminarMedico(Integer idMedico) throws Exception;
    List<Medico> listarMedicos() throws Exception;
}
