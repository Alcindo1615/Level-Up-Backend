package cl.duoc.levelup.pedido.service;

import cl.duoc.levelup.pedido.dto.PedidoRequest;
import cl.duoc.levelup.pedido.model.Pedido;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Servicio que maneja la lógica de negocio del Pedido
 * y las operaciones en Firestore.
 */
@Service
public class PedidoService {

    private static final String COLLECTION_NAME = "pedidos";

    private final Firestore firestore;

    public PedidoService(Firestore firestore) {
        this.firestore = firestore;
    }

    /**
     * Crea un nuevo pedido en Firestore y devuelve su ID.
     */
    public String crearPedido(PedidoRequest request) throws ExecutionException, InterruptedException {
        String id = UUID.randomUUID().toString();

        Pedido pedido = Pedido.builder()
                .id(id)
                .userEmail(request.getUserEmail())
                .items(request.getItems())
                .total(request.getTotal())
                .totalTexto(request.getTotalTexto())
                .direccion(request.getDireccion())
                .codigoPostal(request.getCodigoPostal())
                .cardLast4(request.getCardLast4()) // opcional
                .estado("PAGADO")                  // o "PENDIENTE" si luego validas el pago
                .fechaCreacion(Instant.now())
                .build();

        ApiFuture<WriteResult> future =
                firestore.collection(COLLECTION_NAME).document(id).set(pedido);

        // Espera la escritura para asegurar que se guardó
        future.get();

        return id;
    }

    /**
     * Obtiene un pedido por su ID.
     */
    public Pedido obtenerPedidoPorId(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef =
                firestore.collection(COLLECTION_NAME).document(id);

        DocumentSnapshot snapshot = docRef.get().get();

        if (!snapshot.exists()) {
            return null;
        }

        return snapshot.toObject(Pedido.class);
    }

    /**
     * Lista todos los pedidos de un usuario por email.
     */
    public List<Pedido> obtenerPedidosPorEmail(String email) throws ExecutionException, InterruptedException {
        CollectionReference colRef = firestore.collection(COLLECTION_NAME);

        Query query = colRef.whereEqualTo("userEmail", email)
                            .orderBy("fechaCreacion", Query.Direction.DESCENDING);

        ApiFuture<QuerySnapshot> future = query.get();

        List<Pedido> pedidos = new ArrayList<>();
        for (DocumentSnapshot doc : future.get().getDocuments()) {
            Pedido p = doc.toObject(Pedido.class);
            if (p != null) {
                pedidos.add(p);
            }
        }
        return pedidos;
    }
}
