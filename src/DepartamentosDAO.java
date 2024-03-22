import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DepartamentosDAO {
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
    public static boolean existe(int codigo) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT Codigo FROM DEPARTAMENTOS WHERE Codigo = ?;");
            ps.setInt(1, codigo);
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
    public static boolean create(Departamentos departamento) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO DEPARTAMENTOS (Codigo, Nombre, Presupuesto) VALUES (?, ?, ?);");
            ps.setInt(1, departamento.getCodigo());
            ps.setString(2, departamento.getNombre());
            ps.setInt(3, departamento.getPresupuesto());
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return rBoolean;
    }

    public static Departamentos read(int codigo) {
        Departamentos departamento = new Departamentos();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DEPARTAMENTOS WHERE Codigo = ?;");
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            rs.next();
            departamento.setCodigo(codigo);
            departamento.setNombre(rs.getString("Nombre"));
            departamento.setPresupuesto(rs.getInt("Presupuesto"));
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return departamento;
    }

    public static boolean update(Departamentos departamento) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE DEPARTAMENTOS SET Nombre = ?, Presupuesto = ? WHERE Codigo = ?;");
            ps.setString(1, departamento.getNombre());
            ps.setInt(2, departamento.getPresupuesto());
            ps.setInt(3, departamento.getCodigo());
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return rBoolean;
    }

    public static boolean delete(int codigo) {
        boolean rBoolean = false;
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM DEPARTAMENTOS WHERE Codigo = ?;");
            ps.setInt(1, codigo);
            rBoolean = ps.executeUpdate() > 0;
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return rBoolean;
    }

    // Array
    public static ArrayList<Departamentos> arrayDepartamentos() {
        ArrayList<Departamentos> listDepartamentos = new ArrayList<>();
        Connection con = conexion();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT Codigo FROM DEPARTAMENTOS;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listDepartamentos.add(read(rs.getInt("Codigo")));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return listDepartamentos;
    }
    // Otros

}