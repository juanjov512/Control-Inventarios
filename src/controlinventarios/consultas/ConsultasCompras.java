/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.consultas;

import controlinventarios.objects.Compras;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class ConsultasCompras {
    
    public void llenarTabla(JTable miTabla) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT C.id as id, U.nombre as usuario, P.nombre, "
                + "precio, kilos, fecha FROM compras C INNER JOIN productos P "
                + "ON C.id_productos = P.id INNER JOIN usuarios U "
                + "ON C.id_usuario = U.id ORDER BY fecha DESC";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID","PROVEEDOR","PRODUCTO",
        "PRECIO","KILOS","FECHA"});
        while(res.next()){
            modelo.addRow(new Object[]{res.getString("id"),res.getString("usuario"),
                res.getString("nombre"), res.getString("precio"), 
                res.getString("kilos"), res.getString("fecha")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }
    
    public void busquedaTabla(JTable miTabla, String proveedor) throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT C.id as id, U.nombre as usuario, P.nombre, "
                + "precio, kilos, fecha FROM compras C INNER JOIN productos P "
                + "ON C.id_productos = P.id INNER JOIN usuarios U "
                + "ON C.id_usuario = U.id WHERE U.nombre LIKE '%"+proveedor+"%' "
                + "ORDER BY fecha DESC";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID","PROVEEDOR","PRODUCTO",
        "PRECIO","KILOS","FECHA"});
        while(res.next()){
            modelo.addRow(new Object[]{res.getString("id"),res.getString("usuario"),
                res.getString("nombre"), res.getString("precio"), 
                res.getString("kilos"), res.getString("fecha")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }
    
    public void agregarRegistros(ArrayList<Compras> lista) throws SQLException{
        ConsultasProductos productos = new ConsultasProductos();
        Connection miConexion = new ConexionBD().realizarConexion();
        ConsultasProveedores proveedor = new ConsultasProveedores();
        Statement sentencia = miConexion.createStatement();
        
        for(int i=0; i < lista.size(); i++){
            String producto = productos.obtenerProducto(lista.get(i).getProducto());
            String fecha = lista.get(i).getFecha();
            String usuario = proveedor.obtenerUsuario(lista.get(i).getProveedor());
            int precio = lista.get(i).getPrecio();
            double kilos = lista.get(i).getKilos();
            String consulta = "INSERT INTO compras (id_productos, precio, kilos,"
                + " fecha, id_usuario) values ('"+producto+"', '"+precio+"', "
                + "'"+kilos+"', '"+fecha+"', '"+usuario+"')";
            sentencia.execute(consulta);
        }
        
        miConexion.close();
    }
    
    public void editarRegistro(Compras c, int id) throws SQLException{
        ConsultasProductos productos = new ConsultasProductos();
        Connection miConexion = new ConexionBD().realizarConexion();
        ConsultasProveedores proveedor = new ConsultasProveedores();
        Statement sentencia = miConexion.createStatement();
        String producto = productos.obtenerProducto(c.getProducto());
        String fecha = c.getFecha();
        String usuario = proveedor.obtenerUsuario(c.getProveedor());
        int precio = c.getPrecio();
        double kilos = c.getKilos();
        String consulta = "UPDATE compras SET id_productos = "+producto+", "
                + "precio = "+precio+", kilos = "+kilos+", fecha = '"+fecha+"', "
                + "id_usuario ="+usuario+" WHERE id = "+id+"";
        sentencia.execute(consulta);
        miConexion.close();
    }
    
    public void eliminarRegistro(int id) throws SQLException{
        Connection miConexion = new ConexionBD().realizarConexion();
        Statement sentencia = miConexion.createStatement();
        String consulta = "DELETE FROM compras where id = "+id+"";
        sentencia.execute(consulta);
        miConexion.close();
    }
    
}
