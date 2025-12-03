package cl.duoc.levelup.usuario.model;

import lombok.Data;

/**
 * Usuario que se guarda en la colección "usuario" de Firestore.
 * Solo contiene los datos básicos del registro.
 */
@Data
public class User {

    // ID del documento en Firestore
    private String id;

    // Nombre completo
    private String displayName;

    // Correo electrónico
    private String email;

    // RUT del usuario
    private String rut;

    // Marca de tiempo de creación (Timestamp de Firestore)
    private Object createdAt;

    public User() {
    }
}
