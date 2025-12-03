package cl.duoc.levelup.pedido.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Item dentro del pedido. Representa un producto del carrito.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItem {

    private String productoCodigo;   // Código del producto
    private String productoNombre;   // Nombre
    private String productoPrecio;   // Precio en string (ej: "22.990 CLP")
    private int cantidad;            // Cantidad
    private double subtotal;         // Subtotal numérico
}
