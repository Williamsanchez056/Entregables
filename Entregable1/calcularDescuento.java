package Entregable1;


abstract class Cliente {
    private int id;
    private String nombre;

    public Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    

    public abstract double calcularDescuento(double subtotal);
}


class ClienteRegular extends Cliente {
    public ClienteRegular(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double calcularDescuento(double subtotal) {
        return 0; 
    }
}


class ClienteVIP extends Cliente {
    private double porcentajeDescuento = 0.10;

    public ClienteVIP(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double calcularDescuento(double subtotal) {
        return subtotal * porcentajeDescuento;
    }
}
