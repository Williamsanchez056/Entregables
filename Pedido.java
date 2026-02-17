import java.util.*;

public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> listaItems; 
    private Calendar fechaCreacion;     


    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.listaItems = new ArrayList<>(); 
        this.fechaCreacion = Calendar.getInstance(); 
    }
    public void agregarProducto(Producto p) throws ProductoNoEncontradoException {
        if (p == null) {
            throw new ProductoNoEncontradoException("El producto no existe.");
        }
        listaItems.add(p);
    }
    public void confirmarPedido() throws PedidoInvalidoException {
        if (listaItems.isEmpty()) {
            throw new PedidoInvalidoException("No se puede confirmar un pedido sin productos.");
        }
        System.out.println("Pedido confirmado exitosamente.");
    }

    public String obtenerFechaFormateada() {
        int dia = fechaCreacion.get(Calendar.DAY_OF_MONTH);
        int mes = fechaCreacion.get(Calendar.MONTH) + 1; 
        int anio = fechaCreacion.get(Calendar.YEAR);
        

        
        return dia + "/" + mes + "/" + anio;
    }
    public double calcularTotal() {
        double total = 0;
        for (Producto p : listaItems) {
            total += p.getPrecio();
        }
        return total;
    }

    public List<Producto> getListaItems() {
        return listaItems;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " | Cliente: " + cliente.getNombre() + " | Fecha: " + obtenerFechaFormateada() + " | Total: $" + calcularTotal() + " | Productos: " + listaItems.size();
    }
}