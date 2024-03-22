public class Empleados {
    // Atributos
    private String dni;
    private String nombre;
    private String apellidos;
    private int departamento;

    // Constructores
    public Empleados() {
        this.dni = null;
        this.nombre = null;
        this.apellidos = null;
        this.departamento = -1;
    }

    // Getters & Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    // toString
    public String toString(boolean linea) {
        String rString;
        if (linea) {
            rString = "DNI: " + getDni() + "\tNombre: " + getNombre() + " " + getApellidos() + "\tDepartamento: " + getDepartamento();
        } else {
            rString = "--- EMPLEADO ---" + "\nDNI: " + getDni() + "\nNombre: " + getNombre() + "\nApellidos: " + getApellidos() + "\nDepartamento: " + getDepartamento();
        }
        return rString;
    }

    // Metodos
}
