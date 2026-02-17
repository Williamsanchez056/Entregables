package Entregable2;
public class Cliente {
    private int id;
    private String nombre;

    public Cliente(int id, String nombre) throws PedidoInvalidoException {
        this.id = id;
        setNombre(nombre); 
    }

    public void setNombre(String nombre) throws PedidoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new PedidoInvalidoException("El nombre no puede estar vacio");
        }

        this.nombre = nombre.trim();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }
}