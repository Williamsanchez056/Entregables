package Entregable2;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class main {
    private static final Scanner teclado = new Scanner(System.in);
    private static final GestionSistema sistema = new GestionSistema();
    private static final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        boolean continuar = true;

        System.out.println("=======================");
        System.out.println("   Gestion de pedidos  ");
        System.out.println("=======================");

        while (continuar) {
            try {
                imprimirMenu();
                String entrada = teclado.nextLine();
                
                switch (entrada) {
                    case "1":
                        sistema.mostrarCatalogo();
                        break;
                    case "2":
                        buscarProductoInteractivos();
                        break;
                    case "3":
                        procesarNuevoPedido();
                        break;
                    case "4":
                        filtrarPedidosPorFecha();
                        break;
                    case "5":
                        System.out.println("Nos vemos luego.");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Esta opcion no existe");
                }
            } catch (Exception e) {
                System.out.println("\nPusiste algo mal" + e.getMessage());
            }
            if (continuar) {
                System.out.println("\n--- Presiona Enter para volver al menú ---");
                teclado.nextLine();
            }
        }
    }

    private static void imprimirMenu() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Ver catálogo de productos");
        System.out.println("2. Buscar un producto por nombre");
        System.out.println("3. Registrar un nuevo pedido");
        System.out.println("4. Ver pedidos de una fecha");
        System.out.println("5. Salir");
        System.out.print("Escoge una opcion: ");
    }

    private static void buscarProductoInteractivos() {
        System.out.print("Escribe el nombre del producto que deseas buscas: ");
        String busqueda = teclado.nextLine();

        if (busqueda.trim().isEmpty()) {
            System.out.println("LA busqueda no puede estar vacia");
            return;
        }

        try {
            List<Producto> resultados = sistema.buscarProductosPorNombre(busqueda.trim());
            System.out.println("\nEncontrado:");
            for (Producto p : resultados) {
                System.out.println("- " + p.getNombre() + " (ID: " + p.getId() + ")");
            }
        } catch (ProductoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void procesarNuevoPedido() {
        try {
            System.out.print("ID del Cliente: ");
            String inputCliente = teclado.nextLine();
            
            if (inputCliente.trim().isEmpty()) {
                System.out.println("Debes escribir un ID de cliente.");
                return;
            }
            
            int idCliente = Integer.parseInt(inputCliente);

            Cliente cliente = sistema.obtenerClientePorId(idCliente);
            Pedido miPedido = new Pedido(idCliente, cliente);

            boolean añadirMas = true;
            while (añadirMas) {
                try {
                    System.out.print("ID del Producto: ");
                    String inputProd = teclado.nextLine();
                    
                    if (inputProd.trim().isEmpty()) {
                        System.out.println("Debes escribir un número.\n");
                        continue;
                    }
                    
                    int idProd = Integer.parseInt(inputProd);

                    if (idProd == 0) {
                        añadirMas = false;
                        break;
                    }

                    System.out.print("Cantidad: ");
                    String inputCantidad = teclado.nextLine();
                    
                    if (inputCantidad.trim().isEmpty()) {
                        System.out.println("Debes escribir una cantidad.\n");
                        continue;
                    }
                    
                    int cantidad = Integer.parseInt(inputCantidad);

                    if (cantidad <= 0) {
                        System.out.println("La cantidad debe ser mayor que 0.\n");
                        continue;
                    }

                    sistema.agregarItemAPedido(miPedido, idProd, cantidad);
                    System.out.println("Producto agregado al pedido.\n");

                } catch (StockInsuficienteException | ProductoNoEncontradoException e) {
                    System.out.println("Error: " + e.getMessage() + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("Debes escribir números válidos.\n");
                }
            }

            try {
                sistema.confirmarPedido(miPedido);
                System.out.println("\nEl pedido se guardo correctamente.");
                System.out.println("Total: $" + miPedido.calcularTotal());
            } catch (PedidoInvalidoException e) {
                System.out.println("Error - Pedido vacío: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("Error - El ID del cliente debe ser un número válido.");
        } catch (PedidoInvalidoException e) {  
            System.out.println("Error - Cliente no encontrado: " + e.getMessage());
        }    
    }

    private static void filtrarPedidosPorFecha() {
        System.out.print("Pon una fecha (ejemplo 15/02/2014): ");
        String fechaTexto = teclado.nextLine();

        try {
            Date fecha = formatoFecha.parse(fechaTexto);
            sistema.listarPedidosPorFecha(fecha);
        } catch (ParseException e) {
            System.out.println("Esta fecha no es correcta");
        }
    }
}