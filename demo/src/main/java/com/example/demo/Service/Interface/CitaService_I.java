package com.example.demo.Service.Interface;

import com.example.demo.Models.CitaControl;

import java.util.List;

public interface CitaService_I {
    void insertarCitaControl(CitaControl c) throws Exception;
    void actualizarCitaControl(CitaControl c) throws Exception;
    void eliminarCitaControl(int idCita) throws Exception;
    List<CitaControl> listarCitasControl() throws Exception;
}
