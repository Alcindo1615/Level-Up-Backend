package cl.duoc.levelup.usuario.controller;

import cl.duoc.levelup.usuario.model.User;
import cl.duoc.levelup.usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que expone los endpoints del microservicio de usuario.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite peticiones desde la app móvil / web
public class UserController {

    private final UserService userService;

    /**
     * Endpoint para crear un usuario.
     * Recibe JSON con displayName, email y rut.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User created = userService.createUser(user);
        return ResponseEntity.ok(created);
    }

    /**
     * Endpoint para obtener un usuario por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) throws Exception {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
