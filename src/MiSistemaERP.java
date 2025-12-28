import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class MiSistemaERP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] productos = new String[10];
        double[] precios = new double[10];
        int[] stock = new int[10];
        int opcion;
        boolean salir = false;
        File clientes = new File("Archivos/clientes.txt");
        HashMap <String, Integer> listaClientes = new HashMap<>();

        do{
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║         MINI-ERP v0.1              ║");
            System.out.println("║    Sistema de Inventario           ║");
            System.out.println("╚════════════════════════════════════╝\n");

            System.out.println("1. Ver inventario completo");
            System.out.println("2. Buscar producto");
            System.out.println("3. Añadir stock");
            //System.out.println("4. Vender producto");
            //System.out.println("5. Ver alertas de restock");
            //System.out.println("6. Calcular valor del inventario");
            System.out.println("7. Añadir nuevo producto");
            //System.out.println("8. Ver estadísticas");
            System.out.println("0. Salir");

            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    verInventario(productos, precios, stock);
                    break;

                case 2:
                    buscarProducto(productos, precios, stock, scanner);
                    break;

                case 3:
                    anadirStock(productos, stock, scanner);
                    break;

                case 4:
                    anadirProducto(productos, precios, stock, scanner);
                    break;

                case 5:
                    anadirProducto(productos, precios, stock, scanner);
                    break;

                case 0:
                    salir = true;
                    break;
            }

        }while(!salir);

    }

    public static void verInventario(String[] productos, double[] precios, int[] stock){
        System.out.println("\nInventario de productos:");
        System.out.printf("%-3s  %-25s  %10s  %8s%n", "ID", "Descripción", "Precio €", "Stock");
        System.out.println("-".repeat(52));

        for (int i = 0; i < productos.length; i++) {
            System.out.printf("%-3d  %-25s  %10.2f  %8d%n",
                    (i+1), productos[i], precios[i], stock[i]);
        }
    }

    public static void buscarProducto(String[] productos, double[] precios, int[] stock, Scanner scanner){
        int productoEncontrado = 0;
        boolean find = false;
        String buscar;
        System.out.println("Introduce el producto que quieres buscar: ");
        buscar = scanner.nextLine();
        for (int i = 0; i < productos.length; i++) {
            if(buscar.equalsIgnoreCase(productos[i])){
                productoEncontrado = i;
                find = true;
                break;
            }
        }

        if (find){
            System.out.println("Tenemos tu producto en Stock");
            System.out.println("Descripcion: " + productos[productoEncontrado]);
            System.out.println("Precio: " + precios[productoEncontrado]);
            System.out.println("Stock: " + stock[productoEncontrado]);
        }else {
            System.out.println("No hemos encontrado tu producto!!");
        }
    }

    public static void anadirProducto(String[] productos, double[] precios, int[] stock, Scanner scanner){
        int huecoLibre = 0;
        boolean free = false;

        for (int i = 0; i < productos.length; i++) {
            if(productos[i]==null){
                huecoLibre = i;
                free = true;
                break;
            }
        }

        if (free){
            System.out.println("Introduce el producto: ");
            productos[huecoLibre] = scanner.nextLine();
            System.out.println("Introduce el precio: ");
            precios[huecoLibre] = scanner.nextDouble();
            System.out.println("Introduce el stock: ");
            stock[huecoLibre] = scanner.nextInt();

        }else {
            System.out.println("La base de datos esta llena!");
        }
    }

    public static void anadirStock(String[] productos, int[] stock, Scanner scanner){
        int productoEncontrado = 0;
        boolean find = false;
        String buscar;
        int stockAnadir = 0;
        System.out.println("Introduce el producto que quieres buscar: ");
        buscar = scanner.nextLine();
        for (int i = 0; i < productos.length; i++) {
            if(buscar.equalsIgnoreCase(productos[i])){
                productoEncontrado = i;
                find = true;
                break;
            }
        }

        if (find){
            System.out.println("Indica el stock que quieres añadir o restar:");
            stockAnadir = scanner.nextInt();
            stock[productoEncontrado] += stockAnadir;
        }else {
            System.out.println("No hemos encontrado tu producto!!");
        }
    }

    public static void leerClientes(File clientes, HashMap<String, Integer> listaClientes) {
        String nombre;
        int edad;
        try (BufferedReader br = new BufferedReader(new FileReader(clientes))) {
            String linea;
            // Lee el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                // Divide la línea por la coma
                String[] campos = linea.split(",");
                nombre = campos[0];
                edad = Integer.parseInt(campos[1]);
                // Añade los campos a la lista
                listaClientes.put(nombre, edad);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}