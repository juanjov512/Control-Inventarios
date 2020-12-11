package controlinventarios.consultas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class ConsultasProductos {

    public void llenarTablaProductos(JTable miTabla) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT id,nombre FROM productos";
        Statement sentencia = miConexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID","PRODUCTO"});
        while(resultado.next()){
            modelo.addRow(new Object[]{resultado.getString("id"), resultado.getString("nombre")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }
    
    public void llenarTablaUsuarios(JTable miTabla, String nombre, String tipo) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT id,nombre FROM usuarios WHERE tipo = '"+tipo+"'";
        Statement sentencia = miConexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID",nombre.toUpperCase()});
        while(resultado.next()){
            modelo.addRow(new Object[]{resultado.getString("id"), resultado.getString("nombre")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }
    
    public void llenarCMBProductos(JComboBox cmb) throws SQLException{
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT nombre FROM productos";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        
        while(res.next()){
            cmb.addItem(res.getString("nombre"));
        }
        miConexion.close();
    }
    
    public String obtenerProducto(String nombre) throws SQLException{
       Connection miConexion = new ConexionBD().realizarConexion();
       String consulta = "SELECT id FROM productos WHERE nombre = '"+nombre+"'";
       Statement sentencia = miConexion.createStatement();
       ResultSet res = sentencia.executeQuery(consulta);
       String producto = "";
       if(res.next()){
           producto = res.getString("id");
       }
       miConexion.close();
       return producto;
   }
}

