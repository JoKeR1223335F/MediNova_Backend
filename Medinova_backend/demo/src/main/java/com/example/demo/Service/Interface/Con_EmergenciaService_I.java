package com.example.demo.Service.Interface;

import com.example.demo.Models.ContactoEmergencia;

import java.util.List;

public interface Con_EmergenciaService_I {
    void insertarContactoEmergencia(ContactoEmergencia c) throws Exception;
    void actualizarContactoEmergencia(ContactoEmergencia c) throws Exception;
    void eliminarContactoEmergencia(Integer idContacto) throws Exception;
    List<ContactoEmergencia> listarContactoEmergencia() throws Exception;

}
