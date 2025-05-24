package com.example.demo.Controller;

import com.example.demo.Models.TratamientoItem;
import com.example.demo.Service.Interface.Tratamiento_itemService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamiento-items")
@CrossOrigin(origins = "*")
public class TratamientoItemController {

    @Autowired
    private Tratamiento_itemService_I tratamientoItemService;

    // Listar todos los items de un tratamiento
    @GetMapping("/{idTratamiento}")
    public List<TratamientoItem> listarTratamientoItems(@PathVariable Integer idTratamiento) throws Exception {
        return tratamientoItemService.listarTratamientoItems(idTratamiento);
    }

    // Insertar nuevo tratamiento item
    @PostMapping
    public String insertarTratamientoItem(@RequestBody TratamientoItem tratamientoItem) throws Exception {
        tratamientoItemService.insertarTratamientoItem(tratamientoItem);
        return "Item agregado al tratamiento correctamente.";
    }

    // Actualizar tratamiento item
    @PutMapping
    public String actualizarTratamientoItem(@RequestBody TratamientoItem tratamientoItem) throws Exception {
        tratamientoItemService.actualizarTratamientoItem(tratamientoItem);
        return "Item de tratamiento actualizado correctamente.";
    }

    // Eliminar tratamiento item (requiere idTratamiento y idItem)
    @DeleteMapping("/{idTratamiento}/{idItem}")
    public String eliminarTratamientoItem(@PathVariable Integer idTratamiento, @PathVariable Integer idItem) throws Exception {
        tratamientoItemService.eliminarTratamientoItem(idTratamiento, idItem);
        return "Item de tratamiento eliminado correctamente.";
    }
}