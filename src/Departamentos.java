public class Departamentos {
    // Atributos
    private int codigo;
    private String nombre;
    private int presupuesto;

    // Constructores
    public Departamentos() {
        this.codigo = -1;
        this.nombre = null;
        this.presupuesto = -1;
    }

    // Getters & Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }

    // toString
    public String toString(boolean linea) {
        String rString;
        if (linea) {
            rString = "Codigo: " + getCodigo() + "\tNombre: " + getNombre() + "\tPresupuesto: " + getPresupuesto();
        } else {
            rString = "--- DEPARTAMENTO ---" + "\nCodigo: " + getCodigo() + "\nNombre: " + getNombre() + "\nPresupuesto: " + getPresupuesto();
        }
        return rString;
    }

    // Metodos
}
