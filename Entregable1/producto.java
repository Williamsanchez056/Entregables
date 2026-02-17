package Entregable1;

class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        setPrecio(precio);
        setStock(stock);
    }

    public void setPrecio(double precio) {
        if (precio > 0) this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock >= 0) this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { 
        return nombre; }
    public double getPrecio() { 
        return precio; }
    public int getStock() { 
        return stock; }
}

class DetallePedido {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio(); 
    }

    public double calcularSubtotalDetalle() {
        return cantidad * precioUnitario;
    }

    public Producto getProducto() { 
        return producto; }
    public int getCantidad() { 
        return cantidad; }
}
