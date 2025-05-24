package com.example.demo.Service.Interface;

import com.example.demo.Models.DocMedico;

import java.util.List;

public interface Doc_MedicosService_I {
    void insertarDocMedico(DocMedico d) throws Exception;
    void actualizarDocMedico(DocMedico d) throws Exception;
    void eliminarDocMedico(Integer idDoc) throws Exception;
    List<DocMedico> listarDocMedicos() throws Exception;
}
