package cl.duoc.levelup.contacto.service.controller;

import cl.duoc.levelup.contacto.service.model.Contacto;
import cl.duoc.levelup.contacto.service.service.ContactoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Expone los endpoints REST que usará tu app Android (Retrofit).
 */
@RestController
@RequestMapping("/api/contactos")
@CrossOrigin(origins = "*") // Permite peticiones desde el móvil (si quieres restringir, luego lo ajustas)
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    /**
     * POST /api/contactos
     * Guarda un mensaje de contacto en Firestore.
     */
    @PostMapping
    public ResponseEntity<?> crearContacto(@Valid @RequestBody Contacto contacto) {
        try {
            Contacto guardado = contactoService.guardarContacto(contacto);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (ExecutionException | InterruptedException e) {
            // Manejo simple de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el contacto: " + e.getMessage());
        }
    }

    /**
     * GET /api/contactos
     * Devuelve todos los contactos (útil para pruebas en Postman).
     */
    @GetMapping
    public ResponseEntity<?> obtenerContactos() {
        try {
            List<Contacto> contactos = contactoService.obtenerTodos();
            return ResponseEntity.ok(contactos);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los contactos: " + e.getMessage());
        }
    }

    /**
     * GET /api/contactos/{id}
     * Busca un contacto por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        try {
            Contacto contacto = contactoService.obtenerPorId(id);
            if (contacto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró el contacto con id: " + id);
            }
            return ResponseEntity.ok(contacto);
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el contacto: " + e.getMessage());
        }
    }
}
