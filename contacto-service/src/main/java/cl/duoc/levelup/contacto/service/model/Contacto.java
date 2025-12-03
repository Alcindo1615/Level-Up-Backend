package cl.duoc.levelup.contacto.service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un mensaje enviado desde el formulario de contacto del móvil.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {

    // ID del documento en Firestore
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato válido")
    private String correo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;
}
