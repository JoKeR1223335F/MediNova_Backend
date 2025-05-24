package com.example.demo.Service.Interface;

import com.example.demo.Models.Tratamiento;
import com.example.demo.Models.TratamientoItem;

import java.util.List;

public interface Tratamiento_itemService_I {
    void insertarTratamientoItem(TratamientoItem ti) throws Exception;
    void actualizarTratamientoItem(TratamientoItem ti) throws Exception;
    void eliminarTratamientoItem(Integer idTratamiento, Integer idItem) throws Exception;
    List<TratamientoItem> listarTratamientoItems(Integer idTratamiento) throws Exception;

}
