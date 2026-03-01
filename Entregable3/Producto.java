package Entregable3;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
    public int getId(){
        return id;
    }
    public String getnombre(){
        return nombre;
    }
    public double getprecio(){
        return precio;
    }
    public int getstock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
}
