package com.example.demo.Service.Interface;

import com.example.demo.Models.Tratamiento;

import java.util.List;

public interface TratamientoService_I {
    void  insertarTratamiento(Tratamiento t) throws Exception;
    void actualizarTratamiento(Tratamiento t) throws Exception;
    void eliminarTratamiento(Integer idTratamiento) throws Exception;
    List<Tratamiento> buscarTratamientosPorIdPaciente(Integer idPaciente) throws Exception;

    List<Tratamiento> listarTratamientos() throws Exception;
    List<Tratamiento> buscarTratamientosPorPaciente(int idPaciente) throws Exception;
}
