package com.example.demo.Controller;

import com.example.demo.Models.DocMedico;
import com.example.demo.Service.Interface.Doc_MedicosService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<DocMedico>> obtenerDocMedicosPorUsuario(@PathVariable int idUsuario) {
        try {
            List<DocMedico> lista = docMedicosService.buscarDocMedicosPorUsuario(idUsuario);
            if (lista.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/listar")
    public List<DocMedico> listarDocsMedicos() throws Exception {
        return docMedicosService.listarDocMedicos();
    }
}
