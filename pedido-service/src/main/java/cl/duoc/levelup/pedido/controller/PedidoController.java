package cl.duoc.levelup.pedido.controller;

import cl.duoc.levelup.pedido.dto.PedidoRequest;
import cl.duoc.levelup.pedido.model.Pedido;
import cl.duoc.levelup.pedido.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador REST del microservicio de Pedido.
 */
@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*") // ⚠️ Ajusta para tu frontend si es necesario
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * Endpoint para crear un pedido desde la app móvil.
     */
    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody PedidoRequest request) {
        try {
            String id = pedidoService.crearPedido(request);
            return ResponseEntity.ok().body("Pedido creado con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al crear pedido: " + e.getMessage());
        }
    }

    /**
     * Obtiene un pedido por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        try {
            Pedido pedido = pedidoService.obtenerPedidoPorId(id);
            if (pedido == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(pedido);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al obtener pedido: " + e.getMessage());
        }
    }

    /**
     * Lista todos los pedidos de un usuario por email.
     */
    @GetMapping("/usuario/{email}")
    public ResponseEntity<?> obtenerPorUsuario(@PathVariable String email) {
        try {
            List<Pedido> pedidos = pedidoService.obtenerPedidosPorEmail(email);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al listar pedidos: " + e.getMessage());
        }
    }
}
