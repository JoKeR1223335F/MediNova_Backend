package com.example.demo.Controller;

import com.example.demo.Models.Pago;
import com.example.demo.Service.Interface.PagoService_I;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/actualizar")
    public String actualizarPago(@RequestBody Pago p) throws Exception {
        pagoService.actualizarPago(p);
        return "Pago actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{idPago}")
    public String eliminarPago(@PathVariable int idPago) throws Exception {
        pagoService.eliminarPago(idPago);
        return "Pago eliminado correctamente.";
    }

    @GetMapping("/listar")
    public List<Pago> listarPagos() throws Exception {
        return pagoService.listarPagos();
    }
}
