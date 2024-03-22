import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadosDAO {
    // Conectar
    public static Connection conexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IKEA", "root", "Tarde2022");
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return con;
    }

    // Existe
    public static boolean existe(String dni) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT DNI FROM EMPLEADOS WHERE DNI = ?;");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            rBoolean = rs.next();
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return rBoolean;
    }

    // CRUD
    public static boolean create(Empleados empleado) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO EMPLEADOS (DNI, Nombre, Apellidos, Departamento) VALUES (?, ?, ?, ?);");
            ps.setString(1, empleado.getDni());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellidos());
            ps.setInt(4, empleado.getDepartamento());
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return rBoolean;
    }

    public static Empleados read(String dni) {
        Empleados empleado = new Empleados();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM EMPLEADOS WHERE DNI = ?;");
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            rs.next();
            empleado.setDni(rs.getString("DNI"));
            empleado.setNombre(rs.getString("Nombre"));
            empleado.setApellidos(rs.getString("Apellidos"));
            empleado.setDepartamento(rs.getInt("Departamento"));
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return empleado;
    }

    public static boolean update(Empleados empleado) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE EMPLEADOS SET Nombre = ?, Apellidos = ?, Departamento = ? WHERE DNI = ?;");
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setInt(3, empleado.getDepartamento());
            ps.setString(4, empleado.getDni());
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return rBoolean;
    }

    public static boolean delete(String dni) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM EMPLEADOS WHERE DNI = ?;");
            ps.setString(1, dni);
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return rBoolean;
    }

    // Array
    public static ArrayList<Empleados> arrayEmpleados() {
        ArrayList<Empleados> listEmpleados = new ArrayList<>();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT DNI FROM EMPLEADOS;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listEmpleados.add(read(rs.getString("DNI")));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return listEmpleados;
    }

    // Otros
}