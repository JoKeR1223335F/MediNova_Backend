package com.example.demo.Controller;

import com.example.demo.Models.DocMedico;
import com.example.demo.Service.Interface.Doc_MedicosService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/docsMedicos")
@CrossOrigin(origins = "*")
public class DocMedicoController {

    @Autowired
    private Doc_MedicosService_I docMedicosService;

    @PostMapping("/crear")
    public String crearDocMedico(@RequestBody DocMedico d) throws Exception {
        docMedicosService.insertarDocMedico(d);
        return "Documento médico registrado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarDocMedico(@RequestBody DocMedico d) throws Exception {
        docMedicosService.actualizarDocMedico(d);
        return "Documento médico actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarDocMedico(@PathVariable Integer id) throws Exception {
        docMedicosService.eliminarDocMedico(id);
        return "Documento médico eliminado correctamente.";
    }

    @GetMapping("/listar")
    public List<DocMedico> listarDocsMedicos() throws Exception {
        return docMedicosService.listarDocMedicos();
    }
}
