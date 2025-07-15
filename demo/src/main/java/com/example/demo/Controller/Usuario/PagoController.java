package com.example.demo.Controller.Usuario;

import com.example.demo.Models.Pago;
import com.example.demo.Models.Usuario;
import com.example.demo.Service.Interface.PagoService_I;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoService_I pagoService;

    // Mostrar la página facturacion.html con la lista de pagos
    @GetMapping("")
    public String mostrarFacturacion(Model model, HttpSession session) {
        try {
            // 1️⃣ Obtener usuario logueado desde sesión
            Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

            if (usuarioLogueado == null) {
                return "redirect:/"; // No hay sesión, regresar a login
            }

            // 2️⃣ Obtener lista de pagos
            List<Pago> pagos = pagoService.listarPagos();
            model.addAttribute("pagos", pagos);  // Se pasan los pagos a la vista

            // 3️⃣ Retornar vista según rol
            if (usuarioLogueado.getRol().equalsIgnoreCase("recepcionista")
                    || usuarioLogueado.getRol().equalsIgnoreCase("medico")) {
                return "Trabajador/facturacion";
            } else {
                return "Usuario/facturacion";
            }

        } catch (Exception e) {
            // En caso de error, devolver la vista con mensaje de error
            model.addAttribute("error", "Error al cargar los pagos");
            return "ERRORS/error";
        }
    }


    // Aquí puedes dejar los métodos API que necesites para JSON, pero separados o en otro controlador

    @PostMapping("/crear")
    @ResponseBody
    public String crearPago(@RequestBody Pago p) throws Exception {
        pagoService.insertarPago(p);
        return "Pago registrado correctamente.";
    }

    @GetMapping("/paciente/{idPaciente}")
    @ResponseBody
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
    @ResponseBody
    public String actualizarPago(@RequestBody Pago p) throws Exception {
        pagoService.actualizarPago(p);
        return "Pago actualizado correctamente.";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Pago> listarPagos() throws Exception {
        return pagoService.listarPagos();
    }
}
