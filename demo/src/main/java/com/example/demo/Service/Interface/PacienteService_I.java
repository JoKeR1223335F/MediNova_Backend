package com.example.demo.Service.Interface;

import com.example.demo.Models.Paciente;
import com.example.demo.Models.Usuario;

import java.util.List;

public interface PacienteService_I {
    void actualizarPaciente(Paciente p) throws Exception;

    List<Paciente> listarPacientes() throws Exception;
}
