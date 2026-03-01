package Entregable3;

public class Pedido {
    private int id;
    private Cliente cliente;
    private String fecha;
    private String estado;
    private double total;

    public Pedido(int id, Cliente cliente, String fecha, String estado, double total){
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }
    public int getId(){
        return id;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public String getFecha(){
        return fecha; 
    }
    public String getEstado(){
        return estado;
    }
    public double getTotal(){
        return total;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
}
