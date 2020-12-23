package controlinventarios.consultas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class ConsultasProductos {

    public void llenarTablaProductos(JTable miTabla, String producto) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "";
        if (producto != "") {
            consulta = "SELECT id,nombre FROM productos WHERE nombre LIKE "
                    + "'%" + producto + "%'";
        } else {
            consulta = "SELECT id,nombre FROM productos";
        }
        Statement sentencia = miConexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID", "PRODUCTO"});
        while (resultado.next()) {
            modelo.addRow(new Object[]{resultado.getString("id"), resultado.getString("nombre")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }

    public void llenarTablaUsuarios(JTable miTabla, String nombre, String tipo,
            String usuario) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "";
        if (usuario != "") {
            consulta = "SELECT id,nombre FROM usuarios WHERE tipo = '"
                    + tipo + "' AND nombre LIKE '%" + usuario + "%'";
        } else {
            consulta = "SELECT id,nombre FROM usuarios WHERE tipo = '" + tipo + "'";
        }
        Statement sentencia = miConexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID", nombre.toUpperCase()});
        while (resultado.next()) {
            modelo.addRow(new Object[]{resultado.getString("id"), resultado.getString("nombre")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }

    public void llenarCMBProductos(JComboBox cmb) throws SQLException {
        cmb.removeAllItems();
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT nombre FROM productos";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);

        while (res.next()) {
            cmb.addItem(res.getString("nombre"));
        }
        miConexion.close();
    }

    public String obtenerProducto(String nombre) throws SQLException {
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT id FROM productos WHERE nombre = '" + nombre + "'";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        String producto = "";
        if (res.next()) {
            producto = res.getString("id");
        }
        miConexion.close();
        return producto;
    }

    public void agregarProducto(String nombre) throws SQLException {
        if (obtenerProducto(nombre) == "") {
            Connection miConexion = new ConexionBD().realizarConexion();
            String consulta = "INSERT INTO productos (nombre) values ('" + nombre + "')";
            Statement sentencia = miConexion.createStatement();
            sentencia.execute(consulta);
            miConexion.close();
        } else {
            JOptionPane.showMessageDialog(null, "el producto: " + nombre + " ya existe");
        }
    }

    public void agregarUsuario(String nombre, String tipo) throws SQLException {
        if (existeUsuario(tipo, nombre) == "") {
            Connection miConexion = new ConexionBD().realizarConexion();
            String consulta = "INSERT INTO usuarios (nombre, tipo) values "
                    + "('" + nombre + "', '" + tipo + "')";
            Statement sentencia = miConexion.createStatement();
            sentencia.execute(consulta);
            miConexion.close();
        } else {
            JOptionPane.showMessageDialog(null, "el " + tipo + ": " + nombre + " ya existe");
        }
    }

    public String existeUsuario(String tipo, String nombre) throws SQLException {
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT id,nombre FROM usuarios "
                + "WHERE tipo = '" + tipo + "' AND nombre = '" + nombre + "'";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        String producto = "";
        if (res.next()) {
            producto = res.getString("id");
        }
        miConexion.close();
        return producto;
    }

    public void editarUsuario(int id, String nombre, String tabla) throws SQLException {
        if (tabla == "usuarios" && (existeUsuario("proveedor", nombre) != ""
                || existeUsuario("cliente", nombre) != "")) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con"
                    + " este nombre", "Error!", JOptionPane.ERROR_MESSAGE);
        } else if (tabla == "productos" && obtenerProducto(nombre) != "") {
            JOptionPane.showMessageDialog(null, "Ya existe un producto con"
                    + " este nombre", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            Connection miConexion = new ConexionBD().realizarConexion();
            String consulta = "UPDATE " + tabla + " SET nombre = '" + nombre
                    + "' WHERE id = " + id;
            Statement sentencia = miConexion.createStatement();
            sentencia.execute(consulta);
            miConexion.close();
        }
    }

    public void eliminarRegistro(int id, String tabla) throws SQLException {
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "DELETE FROM "+tabla+" WHERE id = "+id;
        Statement sentencia = miConexion.createStatement();
        sentencia.execute(consulta);
        miConexion.close();
    }

}
