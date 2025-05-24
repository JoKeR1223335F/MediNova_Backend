package com.example.demo.Controller;

import com.example.demo.Models.Inventario;
import com.example.demo.Service.Interface.InventarioService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private InventarioService_I inventarioService;

    @PostMapping("/crear")
    public String crearInventario(@RequestBody Inventario i) throws Exception {
        inventarioService.insertarInventario(i);
        return "Item de inventario registrado correctamente.";
    }

    @PutMapping("/actualizar")
    public String actualizarInventario(@RequestBody Inventario i) throws Exception {
        inventarioService.actualizarInventario(i);
        return "Item de inventario actualizado correctamente.";
    }

    @DeleteMapping("/eliminar/{idItem}")
    public String eliminarInventario(@PathVariable int idItem) throws Exception {
        inventarioService.eliminarInventario(idItem);
        return "Item de inventario eliminado correctamente.";
    }

    @GetMapping("/listar")
    public List<Inventario> listarInventario() throws Exception {
        return inventarioService.listarInventario();
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<Inventario> obtenerInventarioPorId(@PathVariable int idItem) {
        try {
            Inventario inventario = inventarioService.buscarInventarioPorId(idItem);
            if (inventario != null) {
                return ResponseEntity.ok(inventario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}