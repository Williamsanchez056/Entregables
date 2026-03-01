package Entregable3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {

    private List<Producto> productos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    
    public synchronized void procesarPedidos() {

        for (Pedido p : pedidos) {

            if (p.getEstado().equals("Listo")) {

                System.out.println("Pedido Procesado" + p.getId());

                p.setEstado("Procesado");

                guardarPedidos();
            }
        }
    }

    public Cliente buscarCliente(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public synchronized void guardarProductos() {

        try (DataOutputStream dos =  new DataOutputStream( new FileOutputStream("productos.dat"))) {

            for (Producto p : productos) {

                dos.writeInt(p.getId());
                dos.writeUTF(p.getnombre());
                dos.writeDouble(p.getprecio());
                dos.writeInt(p.getstock());
            }

        } catch (IOException e) {
            System.out.println("Error guardando productos");
        }
    }

    public synchronized void guardarPedidos() {

        try (PrintWriter pw = new PrintWriter( new FileWriter("pedidos.txt"))) {

            for (Pedido p : pedidos) {

                pw.println(
                        p.getId() + "|" + p.getCliente().getId() + "|" +  p.getFecha() + "|" + p.getEstado() + "|" +  p.getTotal()
                );
            }

        } catch (IOException e) {
            System.out.println("Error guardando pedidos");
        }
    }

    public synchronized void generarReporte() {

        try (PrintWriter pw =  new PrintWriter(  new FileWriter("reporte_sistema.txt"))) {

            pw.println("REPORTE DEL SISTEMA");
            pw.println("Total productos: " + productos.size());
            pw.println("Total clientes: " + clientes.size());

            int pendientes = 0;
            int confirmados = 0;
            int procesados = 0;

            for (Pedido p : pedidos) {

                if (p.getEstado().equals("PENDIENTE"))
                    pendientes++;

                if (p.getEstado().equals("CONFIRMADO"))
                    confirmados++;

                if (p.getEstado().equals("PROCESADO"))
                    procesados++;
            }

            pw.println("Pendientes: " + pendientes);
            pw.println("Confirmados: " + confirmados);
            pw.println("Procesados: " + procesados);

        } catch (IOException e) {
            System.out.println("Error generando reporte");
        }
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
        guardarProductos();
    }

    public void agregarCliente(Cliente c) {
        clientes.add(c);
    }

    public void agregarPedido(Pedido p) {
        pedidos.add(p);
        guardarPedidos();
    }
}