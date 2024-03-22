import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    // Scanner
    public static Scanner sc = new Scanner(System.in);

    // Main
    public static void main(String[] args) {
        // Variables
        byte uIndice = -1;

        // Programa
        do {
            try {
                indice();
                uIndice = Byte.parseByte(sc.nextLine());

                switch (uIndice) {
                    case 0 -> System.out.println("Cerrando el programa...");
                    case 1 -> addEmpleado(pedirDNI());
                    case 2 -> modificarDepartamento(pedirDepartamento());
                    case 3 -> presuMax(pedirPresupuesto());
                    case 4 -> deleteEmpleado(pedirDNI());
                    case 5 -> exportarBD();
                    default -> System.out.println("Indice no valido");
                } // switch end
            } catch (Exception e) {
                System.out.println("Main Error: " + e.getMessage());
            }
        } while (uIndice != 0);
        sc.close();
    } // main end

    // Indice
    public static void indice() {
        System.out.println("""
                                
                ***** INDICE *****
                1: Añade un empleado
                2: Modificar presupuesto departamento
                3: Buscar por presupuesto maximo
                4: Borrar empleado
                5: Exportar
                """);
    } // indice end

    // Pedir al usuario
    public static boolean pedirConfirmacion() {
        boolean state = true;
        boolean rBoolean = false;
        do {
            System.out.println("¿Desea realizar la operación? Y/n");
            String value = sc.nextLine().toLowerCase();
            switch (value) {
                case "":
                case "y":
                case "yes":
                    rBoolean = true;
                    state = false;
                    break;
                case "n":
                case "no":
                    System.out.println("Operación cancelada...");
                    state = false;
                    break;
                default:
                    System.out.println("Y/n");
            }
        } while (state);
        return rBoolean;
    }

    public static String pedirDNI() {
        System.out.println("Introduzca DNI:");
        return sc.nextLine();
    }

    public static String pedirNombre() {
        System.out.println("Introduza nombre:");
        return sc.nextLine();
    }

    public static String pedirApellidos() {
        System.out.println("Introduzca apellidos:");
        return sc.nextLine();
    }

    public static int pedirDepartamento() {
        System.out.println("Introduzca codigo departamento:");
        return Integer.parseInt(sc.nextLine());
    }

    public static int pedirPresupuesto() {
        System.out.println("Introduzca presupuesto:");
        return Integer.parseInt(sc.nextLine());
    }

    // Insertar Empleado
    public static void addEmpleado(String dni) {
        if (!EmpleadosDAO.existe(dni)) {
            Empleados empleado = new Empleados();
            empleado.setDni(dni);
            empleado.setNombre(pedirNombre());
            empleado.setApellidos(pedirApellidos());
            empleado.setDepartamento(pedirDepartamento());
            System.out.println(empleado.toString(false));
            if (pedirConfirmacion()) {
                if (EmpleadosDAO.create(empleado)) {
                    System.out.println("Empleado creado");
                }
            }
        } else {
            System.out.println("Ya hay un empleado con ese codigo");
        }
    } // add empleado end

    // Modificar Departamento
    public static void modificarDepartamento(int codigo) {
        if (DepartamentosDAO.existe(codigo)) {
            Departamentos departamento = DepartamentosDAO.read(codigo);
            System.out.println(departamento.toString(false));
            departamento.setPresupuesto(pedirPresupuesto());
            System.out.println(departamento.toString(false));
            if (pedirConfirmacion()) {
                if (DepartamentosDAO.update(departamento)) {
                    System.out.println("Departamento modificado");
                }
            }
        } else {
            System.out.println("No existe ningún departamento con ese codigo");
        }
    } // modificarDepartamento end

    // Eliminar Empleado
    public static void deleteEmpleado(String dni) {
        if (EmpleadosDAO.existe(dni)) {
            Empleados empleado = EmpleadosDAO.read(dni);
            System.out.println(empleado.toString(false));
            if (pedirConfirmacion()) {
                if (EmpleadosDAO.delete(dni)) {
                    System.out.println("Empleado eliminado");
                }
            }
        } else {
            System.out.println("No hay nigun empleado con ese codigo");
        }
    } // deleteEmpleado end

    // Buscar por presupuesto maximo
    public static void presuMax(int MaxPresu) {
        for (Departamentos departamento : DepartamentosDAO.arrayDepartamentos()) {
            if (departamento.getPresupuesto() < MaxPresu) {
                System.out.println(departamento.toString(true));
            }
        }
    }


    // Exportar Base de Datos
    public static void exportarBD() {
        StringBuilder stringDepartamentos = new StringBuilder();
        StringBuilder stringEmpleados = new StringBuilder();
        FileWriter fwBase;
        try {
            fwBase = new FileWriter("txt/Departamentos.txt");
            for (Departamentos departamento : DepartamentosDAO.arrayDepartamentos()) {
                stringDepartamentos.append(departamento.toString(true)).append("\n");
            }
            fwBase.write(stringDepartamentos.toString());
            fwBase.close();
            fwBase = new FileWriter("txt/Empleados.txt");
            for (Empleados empleado : EmpleadosDAO.arrayEmpleados()) {
                stringEmpleados.append(empleado.toString(true)).append("\n");
            }
            fwBase.write(stringEmpleados.toString());
            fwBase.close();
        } catch (Exception e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
} // class end