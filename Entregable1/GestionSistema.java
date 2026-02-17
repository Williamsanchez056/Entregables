package Entregable1;

import java.util.Scanner;

public class GestionSistema {
    private Producto[] productos = new Producto[50];
    private Cliente[] clientes = new Cliente[50];
    private Pedido[] pedidos = new Pedido[50];
    
    private int contP = 0, contC = 0, contPed = 0;
    private Scanner sc = new Scanner(System.in);

    public void registrarProducto() {
        System.out.print("ID: "); int id = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Precio: "); double pre = sc.nextDouble();
        System.out.print("Stock: "); int st = sc.nextInt();
        productos[contP++] = new Producto(id, nom, pre, st);
    }

    public void registrarCliente() {
        System.out.print("ID: "); int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Tipo (1-Regular, 2-VIP): "); int tipo = sc.nextInt();
        
        if (tipo == 2) clientes[contC++] = new ClienteVIP(id, nom);
        else clientes[contC++] = new ClienteRegular(id, nom);
    }

    public void crearPedido() {
        System.out.print("ID Pedido: "); int idPed = sc.nextInt();
        System.out.print("ID Cliente: "); int idCli = sc.nextInt();
        
        Cliente clienteEncontrado = buscarCliente(idCli);
        if (clienteEncontrado != null) {
            pedidos[contPed++] = new Pedido(idPed, clienteEncontrado, 10);
            System.out.println("Pedido creado en BORRADOR.");
        }
    }

    public void cambiarEstadoPedido() {
        System.out.print("ID Pedido: "); int id = sc.nextInt();
        Pedido p = buscarPedido(id);
        if (p != null) {
            System.out.print("Nuevo estado (1-CONFIRMAR, 2-CANCELAR): ");
            int op = sc.nextInt();
            if (op == 1 && p.getContadorDetalles() > 0) {
                p.setEstado("CONFIRMADO");
                actualizarStock(p, -1); 
            } else if (op == 2) {
                if (p.getEstado().equals("CONFIRMADO")) actualizarStock(p, 1); // Devolver
                p.setEstado("CANCELADO");
            }
        }
    }

    private void actualizarStock(Pedido p, int factor) {
        for (int i = 0; i < p.getContadorDetalles(); i++) {
            Producto prod = p.getDetalles()[i].getProducto();
            int cantidad = p.getDetalles()[i].getCantidad();
            prod.setStock(prod.getStock() + (cantidad * factor));
        }
    }


    private Cliente buscarCliente(int id) {
        for (int i = 0; i < contC; i++) if (clientes[i].getId() == id) 
            return clientes[i];
        return null;
    }
    
    private Pedido buscarPedido(int id) {
        for (int i = 0; i < contPed; i++) if (pedidos[i].getId() == id) 
            return pedidos[i];
        return null;
    }
}

