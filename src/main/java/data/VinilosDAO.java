package data;

import static data.Conexion.*;
import java.sql.*;
import java.util.*;
import model.Vinilos;

public class VinilosDAO {
    private static final String SQL_CREATE="INSERT INTO vinilos(nombre, autor, cantCanciones, precio, copias) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_READ="SELECT * FROM vinilos";
    private static final String SQL_READ_BY_ID= "SELECT * FROM vinilos WHERE idvinilos= ?";
    private static final String SQL_UPDATE_PRECIO="UPDATE vinilos SET precio = ? WHERE idvinilos = ?";
    private static final String SQL_UPDATE_COPIAS="UPDATE vinilos SET copias = ? WHERE idvinilos = ?";
    private static final String SQL_UPDATE="UPDATE vinilos SET nombre = ?, autor = ?, cantCanciones = ?, precio = ?,copias = ? WHERE idvinilos = ?";
    private static final String SQL_DELETE="DELETE FROM vinilos WHERE idvinilos = ?";
    
    
    public List<Vinilos> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vinilos vinilo;
        List<Vinilos> vinilos = new ArrayList();

        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int idvinilos = rs.getInt(1);
                String nombre = rs.getString(2);
                String autor = rs.getString(3);
                int cantCanciones = rs.getInt(4);
                double precio = rs.getDouble(5);
                int copias = rs.getInt(6);

                vinilo = new Vinilos(idvinilos, nombre, autor,cantCanciones,precio,copias);

                vinilos.add(vinilo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return vinilos;
    }
    
    public Vinilos findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vinilos vinilo = null;
        
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_READ_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                int idvinilos = rs.getInt(1);
                String nombre = rs.getString(2);
                String autor = rs.getString(3);
                int cantCanciones = rs.getInt(4);
                double precio = rs.getDouble(5);
                int copias = rs.getInt(6);

                vinilo = new Vinilos(idvinilos, nombre, autor,cantCanciones,precio,copias);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return vinilo;
    }
    
    
    public int insert(Vinilos vinilo){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_CREATE);
            stmt.setString(1, vinilo.getNombre());
            stmt.setString(2, vinilo.getAutor());
            stmt.setInt(3, vinilo.getCantCanciones());
            stmt.setDouble(4, vinilo.getPrecio());
            stmt.setInt(5, vinilo.getCopias());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int updatePrecio(Vinilos vinilo){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_PRECIO);
            stmt.setDouble(1, vinilo.getPrecio());
            stmt.setInt(2, vinilo.getIdvinilo());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int updateCopias(Vinilos vinilo){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE_COPIAS);
            stmt.setInt(1, vinilo.getCopias());
            stmt.setInt(2, vinilo.getIdvinilo());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int update(Vinilos vinilo){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, vinilo.getNombre());
            stmt.setString(2, vinilo.getAutor());
            stmt.setInt(3, vinilo.getCantCanciones());
            stmt.setDouble(4, vinilo.getPrecio());
            stmt.setInt(5, vinilo.getCopias());
            stmt.setInt(6, vinilo.getIdvinilo());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int deleteVinilo(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConexion();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
