package Entregable3;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner sc = new Scanner(System.in);
        
        Thread hiloProcesador = new Thread(new HiloProcesador(sistema));
        hiloProcesador.start();

        Thread hiloDemonio = new Thread(new HiloDemonio(sistema));
        hiloDemonio.setDaemon(true);
        hiloDemonio.start();

        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE PEDIDOS ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Agregar cliente");
            System.out.println("3. Agregar pedido");
            System.out.println("4. Confirmar pedido");
            System.out.println("5. Salir");
            System.out.print("Seleccione: ");
            
            opcion = sc.nextInt();
            sc.nextLine(); 

            if (opcion == 1) {
                System.out.print("ID Producto: ");
                int id = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Precio: ");
                double precio = sc.nextDouble();

                System.out.print("Stock: ");
                int stock = sc.nextInt();

                sistema.agregarProducto(new Producto(id, nombre, precio, stock));
                System.out.println("Producto agregado.");
            }

            if (opcion == 2) {
                
                System.out.print("ID Cliente: ");
                int id = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Nombre Completo: ");
                String nombre = sc.nextLine();

                System.out.print("Direccion: ");
                String direccion = sc.nextLine();

                sistema.agregarCliente(new Cliente(id, nombre, direccion));
                System.out.println("Cliente " + nombre + " agregado correctamente.");
            }

            if (opcion == 3) {
                System.out.print("ID Pedido: ");
                int id = sc.nextInt();

                System.out.print("ID Cliente: ");
                int idCliente = sc.nextInt();

                Cliente cliente = sistema.buscarCliente(idCliente); 

                if (cliente != null) {
                    Pedido pedido = new Pedido(id, cliente, "2026-03-01", "PENDIENTE", 100.0);
                    sistema.agregarPedido(pedido);
                    System.out.println("Pedido agregado exitosamente.");
                } else {
                    System.out.println("Error: Cliente no encontrado.");
                }
            }

            if (opcion == 4) {
                System.out.print("ID Pedido a confirmar: ");
                int idPedido = sc.nextInt();
                
                boolean encontrado = false;
                for (Pedido p : sistema.getPedidos()) {
                    if (p.getId() == idPedido) {
                        p.setEstado("CONFIRMADO");
                        sistema.guardarPedidos();
                        System.out.println("Pedido " + idPedido + " confirmado.");
                        encontrado = true;
                        break; 
                    }
                }
                if (!encontrado) System.out.println("Pedido no encontrado.");
            }

        } while (opcion != 5);

        System.out.println("Nos vemos pronto.");
        sc.close();
    }
}