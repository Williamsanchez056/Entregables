package Entregable1;

class Pedido {
    private int id;
    private Cliente cliente;
    private String estado; 
    private DetallePedido[] detalles;
    private int contadorDetalles;

    public Pedido(int id, Cliente cliente, int maxItems) {
        this.id = id;
        this.cliente = cliente;
        this.estado = "BORRADOR";
        this.detalles = new DetallePedido[maxItems];
        this.contadorDetalles = 0;
    }

    public void agregarProducto(Producto p, int cant) {
        if (p.getStock() >= cant) {
            detalles[contadorDetalles++] = new DetallePedido(p, cant);
        }
    }

    public double calcularSubtotal() {
        double sub = 0;
        for (int i = 0; i < contadorDetalles; i++) {
            sub += detalles[i].calcularSubtotalDetalle();
        }
        return sub;
    }

    public double calcularTotal() {
        double sub = calcularSubtotal();
        return sub - cliente.calcularDescuento(sub); 
    }

  
    public int getId() { 
        return id; }
    public String getEstado() { 
        return estado; }
    public void setEstado(String estado) { 
        this.estado = estado; }
    public int getContadorDetalles() { 
        return contadorDetalles; 
    }
    public DetallePedido[] getDetalles() { 
        return detalles; 
    }
}


