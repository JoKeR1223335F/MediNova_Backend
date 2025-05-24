package com.example.demo.Controller;

import com.example.demo.Models.Pago;
import com.example.demo.Service.Interface.PagoService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoService_I pagoService;

    @PostMapping("/crear")
    public String crearPago(@RequestBody Pago p) throws Exception {
        pagoService.insertarPago(p);
        return "Pago registrado correctamente.";
    }

        @GetMapping("/paciente/{idPaciente}")
        public ResponseEntity<List<Pago>> obtenerPagosPorPaciente(@PathVariable int idPaciente) {
            try {
                List<Pago> pagos = pagoService.buscarPagosPorPaciente(idPaciente);
                if (pagos.isEmpty()) {
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.ok(pagos);
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }



    @PutMapping("/actualizar")
    public String actualizarPago(@RequestBody Pago p) throws Exception {
        pagoService.actualizarPago(p);
        return "Pago actualizado correctamente.";
    }

    @GetMapping("/listar")
    public List<Pago> listarPagos() throws Exception {
        return pagoService.listarPagos();
    }
}
