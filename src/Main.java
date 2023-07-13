import java.util.ArrayList;
import java.util.Scanner;

class Material {
    private String nombre;
    private int cantidad;

    public Material(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

class Empleado {
    private String nombre;
    private String apellido;
    private String cargo;
    private String codigoEmpleado;

    public Empleado(String nombre, String apellido, String cargo, String codigoEmpleado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }
}

class SistemaEntregaRecepcion {
    private ArrayList<Material> inventario;
    private ArrayList<Empleado> empleados;
    private Scanner scanner;

    public SistemaEntregaRecepcion() {
        inventario = new ArrayList<>();
        empleados = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void agregarMaterial(Material material) {
        inventario.add(material);
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void entregarMaterial() {
        System.out.println("Stock actual de materiales:");
        mostrarInventario();

        System.out.println("Ingrese el código del empleado:");
        String codigoEmpleado = scanner.nextLine();

        Empleado empleado = buscarEmpleado(codigoEmpleado);
        if (empleado == null) {
            System.out.println("El empleado no está registrado.");
            return;
        }

        System.out.println("Ingrese el nombre del material a entregar:");
        String nombreMaterial = scanner.nextLine();

        Material material = buscarMaterial(nombreMaterial);
        if (material == null) {
            System.out.println("El material no está en el inventario.");
            return;
        }

        if (material.getCantidad() > 0) {
            material.setCantidad(material.getCantidad() - 1);
            System.out.println("Se entregó un " + nombreMaterial + " a " + empleado.getNombreCompleto());
        } else {
            System.out.println("No hay disponibilidad de " + nombreMaterial + " en el inventario.");
        }
    }

    public void recibirMaterial() {
        System.out.println("Stock actual de materiales:");
        mostrarInventario();

        System.out.println("Ingrese el código del empleado:");
        String codigoEmpleado = scanner.nextLine();

        Empleado empleado = buscarEmpleado(codigoEmpleado);
        if (empleado == null) {
            System.out.println("El empleado no está registrado.");
            return;
        }

        System.out.println("Ingrese el nombre del material a recibir:");
        String nombreMaterial = scanner.nextLine();

        Material material = buscarMaterial(nombreMaterial);
        if (material == null) {
            System.out.println("El material no está en el inventario.");
            return;
        }

        material.setCantidad(material.getCantidad() + 1);
        System.out.println("Se recibió un " + nombreMaterial + " de " + empleado.getNombreCompleto());
    }

    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (Material material : inventario) {
            System.out.println(material.getNombre() + ": " + material.getCantidad());
        }
    }

    private Empleado buscarEmpleado(String codigo) {
        for (Empleado empleado : empleados) {
            if (empleado.getCodigoEmpleado().equals(codigo)) {
                return empleado;
            }
        }
        return null;
    }

    private Material buscarMaterial(String nombre) {
        for (Material material : inventario) {
            if (material.getNombre().equalsIgnoreCase(nombre)) {
                return material;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        SistemaEntregaRecepcion sistema = new SistemaEntregaRecepcion();
        Scanner scanner = new Scanner(System.in);

        // Agregar materiales al inventario
        sistema.agregarMaterial(new Material("Casco", 10));
        sistema.agregarMaterial(new Material("Botas", 5));

        // Agregar empleados
        sistema.agregarEmpleado(new Empleado("Juan", "Pérez", "Gerente", "001"));
        sistema.agregarEmpleado(new Empleado("María", "Gómez", "Supervisor", "002"));
        sistema.agregarEmpleado(new Empleado("Dachel", "Olivos", "Encargado", "003"));
        sistema.agregarEmpleado(new Empleado("Julio", "Siancas", "Obrero", "004"));
        sistema.agregarEmpleado(new Empleado("Andre", "Lauda", "Supervisor", "005"));
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n¡Bienvenido al sistema de recepcion y entregas de equipos de seguridad! " +
                    "¿Qué operación desea realizar?");
            System.out.println("1. Entregar material");
            System.out.println("2. Recibir material");
            System.out.println("3. Mostrar inventario");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    sistema.entregarMaterial();
                    break;
                case 2:
                    sistema.recibirMaterial();
                    break;
                case 3:
                    sistema.mostrarInventario();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
}