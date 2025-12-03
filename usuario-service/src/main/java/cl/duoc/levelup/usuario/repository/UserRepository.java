package cl.duoc.levelup.usuario.repository;

import cl.duoc.levelup.usuario.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Repositorio que maneja las operaciones de persistencia en Firestore.
 */
@Repository
@RequiredArgsConstructor
public class UserRepository {

    // Cliente Firestore inyectado desde FirebaseConfig
    private final Firestore firestore;

    private static final String COLLECTION_NAME = "usuario";

    /**
     * Guarda un usuario en Firestore.
     * Si no tiene id, se genera uno nuevo.
     */
    public User save(User user) throws Exception {
        CollectionReference collection = firestore.collection(COLLECTION_NAME);

        DocumentReference docRef;
        if (user.getId() == null || user.getId().isEmpty()) {
            docRef = collection.document();
        } else {
            docRef = collection.document(user.getId());
        }

        // Campos que se guardarán en el documento
        Map<String, Object> data = new HashMap<>();
        data.put("displayName", user.getDisplayName());
        data.put("email", user.getEmail());
        data.put("rut", user.getRut());

        // createdAt se asigna solo si viene nulo (timestamp del servidor)
        if (user.getCreatedAt() == null) {
            data.put("createdAt", FieldValue.serverTimestamp());
        } else {
            data.put("createdAt", user.getCreatedAt());
        }

        // Guardar/actualizar el documento
        ApiFuture<WriteResult> write = docRef.set(data, SetOptions.merge());
        write.get();

        // Devolver el usuario con el id generado
        user.setId(docRef.getId());
        return user;
    }

    /**
     * Busca un usuario por id en Firestore.
     */
    public User findById(String id) throws Exception {
        DocumentSnapshot doc = firestore
                .collection(COLLECTION_NAME)
                .document(id)
                .get()
                .get();

        if (!doc.exists()) {
            return null;
        }

        User user = doc.toObject(User.class);
        if (user != null) {
            user.setId(doc.getId());
        }
        return user;
    }
}
