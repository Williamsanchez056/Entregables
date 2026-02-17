public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) throws PedidoInvalidoException {
        this.id = id;
        setNombre(nombre);
        this.precio = precio;
        this.stock = stock;
    }


    public void setNombre(String nombre) throws PedidoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new PedidoInvalidoException("El nombre del producto no es correcto");
        }

        this.nombre = nombre.trim();
    }

    public void reducirStock(int cantidad) throws StockInsuficienteException {
        if (cantidad > this.stock) {
            throw new StockInsuficienteException("No hay Stock para: " + this.nombre);
        }
        this.stock -= cantidad;
    }

    public int getId() { 
        return id; 
    }

    public String getNombre() { 
        return nombre; 
    }

    public double getPrecio() { 
        return precio; 
    }

    public int getStock() { 
        return stock; 
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " | Precio: $" + precio + " | Stock: " + stock;
    }
}