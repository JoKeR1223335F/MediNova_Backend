package com.example.demo.Service.Interface;

import com.example.demo.Models.Pago;

import java.util.List;

public interface PagoService_I {
    void insertarPago(Pago p) throws Exception;
    void actualizarPago(Pago p) throws Exception;
    List<Pago> buscarPagosPorPaciente(int idPaciente) throws Exception;
    List<Pago> listarPagos() throws Exception;
}
