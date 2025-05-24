package com.example.demo.Service.Interface;

import com.example.demo.Models.Paciente;
import com.example.demo.Models.Usuario;

import java.util.List;

public interface PacienteService_I {
    void insertarPaciente(Paciente p) throws Exception;
    void actualizarPaciente(Paciente p) throws Exception;
    void eliminarPaciente(Integer idPaciente) throws Exception;
    List<Paciente> listarPacientes() throws Exception;
}
