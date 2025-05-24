package com.example.demo.Service.Interface;

import com.example.demo.Models.ContactoEmergencia;

import java.util.List;

public interface Con_EmergenciaService_I {
    void actualizarContactoEmergencia(ContactoEmergencia c) throws Exception;

    List<ContactoEmergencia> listarContactoEmergencia() throws Exception;
    List<ContactoEmergencia> obtenerContactosPorUsuario(Integer idUsuario) throws Exception;

}
