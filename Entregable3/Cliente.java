package Entregable3;

public class Cliente {
    private int id;
    private String nombre;
    private String Direccion;

    public Cliente(int id, String nombre, String Direccion){
        this.id = id;
        this.nombre = nombre;
        this.Direccion = Direccion;
    }
    public int getId(){
        return id;
    }
    public String getnombre(){
        return nombre;
    }
    public String getDireccion(){
        return Direccion;
    }
    
}
