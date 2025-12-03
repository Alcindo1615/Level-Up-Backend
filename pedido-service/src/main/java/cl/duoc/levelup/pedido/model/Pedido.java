package cl.duoc.levelup.pedido.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * Pedido que se guarda en la colección "pedidos" de Firestore.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    private String id;                       // ID del documento en Firestore
    private String userEmail;               // Email del usuario
    private List<PedidoItem> items;         // Lista de items del carrito
    private double total;                   // Total numérico
    private String totalTexto;              // Total formateado (ej: "$882.960 CLP")

    // Datos de envío
    private String direccion;
    private String codigoPostal;

    // Info mínima de tarjeta (opcional, sin datos sensibles)
    private String cardLast4;               // últimos 4 dígitos (opcional)

    // Estado del pedido
    private String estado;                  // ej: "PENDIENTE", "PAGADO"
    private Instant fechaCreacion;          // timestamp
}
