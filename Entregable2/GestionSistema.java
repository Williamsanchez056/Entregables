package Entregable2;
import java.util.*;

public class GestionSistema {
    private List<Producto> catalogo;
    private List<Cliente> clientes;
    private List<Pedido> pedidos;
    private int proximoIdPedido;

    public GestionSistema() {
        this.catalogo = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.proximoIdPedido = 1;
        inicializarDatos();
    }

    private void inicializarDatos() {

        try {
            catalogo.add(new Producto(1, "Laptop", 50.000, 31));
            catalogo.add(new Producto(2, "Mouse", 12.000, 40));
            catalogo.add(new Producto(3, "Teclado", 8.000, 37));
            catalogo.add(new Producto(4, "Monitor", 75.000, 19));
            catalogo.add(new Producto(5, "Auriculares", 15.000, 21));

            clientes.add(new Cliente(1, "Willy Sanchez"));
            clientes.add(new Cliente(2, "Raelvis Gomes"));
            clientes.add(new Cliente(3, "Juan Castillo"));
        } catch (PedidoInvalidoException e) {
            System.out.println("Error inicializando datos: " + e.getMessage());
        }
    }

    public void mostrarCatalogo() {
        System.out.println("\n========== Tu Catalogo ==========");
        if (catalogo.isEmpty()) {
            System.out.println(".");
            return;
        }
        for (Producto p : catalogo) {
            System.out.println(p);
        }
    }

    public List<Producto> buscarProductosPorNombre(String nombre) throws ProductoNoEncontradoException {
        List<Producto> resultados = new ArrayList<>();
        for (Producto p : catalogo) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(p);
            }
        }

        if (resultados.isEmpty()) {
            throw new ProductoNoEncontradoException("No encontramos productos con '" + nombre + "'");
        }

        return resultados;
    }

    public Producto obtenerProductoPorId(int id) throws ProductoNoEncontradoException {
        for (Producto p : catalogo) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("El producto con ID " + id + " no existe.");
    }

    public void agregarItemAPedido(Pedido pedido, int idProducto, int cantidad)
            throws ProductoNoEncontradoException, StockInsuficienteException {
        Producto p = obtenerProductoPorId(idProducto);
        p.reducirStock(cantidad);
        

        for (int i = 0; i < cantidad; i++) {
            pedido.agregarProducto(p);
        }
    }

    public void confirmarPedido(Pedido pedido) throws PedidoInvalidoException {
        pedido.confirmarPedido();
        pedidos.add(pedido);
    }

    public void listarPedidosPorFecha(Date fecha) {
        System.out.println("\n========== PEDIDOS DEL DIA ==========");
        boolean encontrado = false;
        
        for (Pedido p : pedidos) {
            Calendar cal = p.getFechaCreacion();
            if (isSameDay(cal, fecha)) {
                System.out.println(p);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay pedidos en esta fecha.");
        }
    }

    private boolean isSameDay(Calendar cal, Date fecha) {
        Calendar fechaCal = Calendar.getInstance();
        fechaCal.setTime(fecha);
        
        return cal.get(Calendar.YEAR) == fechaCal.get(Calendar.YEAR) && cal.get(Calendar.MONTH) == fechaCal.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) == fechaCal.get(Calendar.DAY_OF_MONTH);
    }

    public Cliente obtenerClientePorId(int id) throws PedidoInvalidoException {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return clientes.get(id - 1); 
            }
        }
        throw new PedidoInvalidoException("El cliente con ID " + id + " no existe.");
    }
}
