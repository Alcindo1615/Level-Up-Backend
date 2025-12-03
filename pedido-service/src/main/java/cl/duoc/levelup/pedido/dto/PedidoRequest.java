package cl.duoc.levelup.pedido.dto;

import cl.duoc.levelup.pedido.model.PedidoItem;
import lombok.Data;

import java.util.List;

/**
 * Objeto que llega desde el frontend (Android).
 */
@Data
public class PedidoRequest {

    private String userEmail;               // Email del usuario logueado
    private List<PedidoItem> items;         // Items del carrito
    private double total;                   // Total numérico
    private String totalTexto;              // Total formateado

    // Datos de envío
    private String direccion;
    private String codigoPostal;

    // Solo últimos 4 dígitos de la tarjeta (opcional)
    private String cardLast4;
}
