/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.consultas;

import controlinventarios.objects.Compras;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class ConsultasCompras {
    
    DecimalFormat formateo = new DecimalFormat("###,###.##");

    public void llenarTabla(JTable miTabla) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT C.id as id, U.nombre as usuario, P.nombre, "
                + "precio, kilos, fecha FROM compras C INNER JOIN productos P "
                + "ON C.id_productos = P.id INNER JOIN usuarios U "
                + "ON C.id_usuario = U.id ORDER BY id DESC";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID", "PROVEEDOR", "PRODUCTO",
            "PRECIO", "KILOS", "FECHA"});
        while (res.next()) {
            modelo.addRow(new Object[]{res.getString("id"), res.getString("usuario"),
                res.getString("nombre"), formateo.format(res.getLong("precio")),
                formateo.format(res.getDouble("kilos")), res.getString("fecha")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }

    public void busquedaTabla(JTable miTabla, String proveedor) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = "SELECT C.id as id, U.nombre as usuario, P.nombre, "
                + "precio, kilos, fecha FROM compras C INNER JOIN productos P "
                + "ON C.id_productos = P.id INNER JOIN usuarios U "
                + "ON C.id_usuario = U.id WHERE U.nombre LIKE '%" + proveedor + "%' "
                + "ORDER BY fecha DESC";
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        modelo.setColumnIdentifiers(new Object[]{"ID", "PROVEEDOR", "PRODUCTO",
            "PRECIO", "KILOS", "FECHA"});
        while (res.next()) {
            modelo.addRow(new Object[]{res.getString("id"), res.getString("usuario"),
                res.getString("nombre"), res.getString("precio"),
                res.getString("kilos"), res.getString("fecha")});
        }
        miTabla.setModel(modelo);
        miConexion.close();
    }

    public void agregarRegistros(ArrayList<Compras> lista) throws SQLException {
        ConsultasProductos productos = new ConsultasProductos();
        Connection miConexion = new ConexionBD().realizarConexion();
        ConsultasProveedores proveedor = new ConsultasProveedores();
        Statement sentencia = miConexion.createStatement();

        for (int i = 0; i < lista.size(); i++) {
            String producto = productos.obtenerProducto(lista.get(i).getProducto());
            String fecha = lista.get(i).getFecha();
            String usuario = proveedor.obtenerUsuario(lista.get(i).getProveedor());
            int precio = lista.get(i).getPrecio();
            double kilos = lista.get(i).getKilos();
            BigInteger total;
            total = BigDecimal.valueOf(lista.get(i).getProducto().equalsIgnoreCase("gastos") ? precio : precio * kilos).toBigInteger();
            String consulta = "INSERT INTO compras (id_productos, precio, kilos,"
                    + " fecha, id_usuario, total) values ('" + producto + "', '" + precio + "', "
                    + "'" + kilos + "', '" + fecha + "', '" + usuario + "', '" + total + "')";
            sentencia.execute(consulta);
        }

        miConexion.close();
    }

    public void editarRegistro(Compras c, int id) throws SQLException {
        ConsultasProductos productos = new ConsultasProductos();
        Connection miConexion = new ConexionBD().realizarConexion();
        ConsultasProveedores proveedor = new ConsultasProveedores();
        Statement sentencia = miConexion.createStatement();
        String producto = productos.obtenerProducto(c.getProducto());
        String fecha = c.getFecha();
        String usuario = proveedor.obtenerUsuario(c.getProveedor());
        int precio = c.getPrecio();
        double kilos = c.getKilos();
        BigInteger total = BigDecimal.valueOf(c.getProducto().equalsIgnoreCase("gastos") ? precio : precio * kilos).toBigInteger();
        String consulta = "UPDATE compras SET id_productos = " + producto + ", "
                + "precio = " + precio + ", kilos = " + kilos + ", fecha = '" + fecha + "', "
                + "id_usuario =" + usuario + ", total = " + total + " WHERE id = " + id + "";
        sentencia.execute(consulta);
        miConexion.close();
    }

    public void eliminarRegistro(int id) throws SQLException {
        Connection miConexion = new ConexionBD().realizarConexion();
        Statement sentencia = miConexion.createStatement();
        String consulta = "DELETE FROM compras where id = " + id + "";
        sentencia.execute(consulta);
        miConexion.close();
    }

    public void reportes(JTable miTabla, Date fechaMin, Date fechaMax,
            JLabel lblCompras, JLabel lblGastos, JLabel lblTotal, String tabla) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.setColumnIdentifiers(new Object[]{"PRODUCTO", "KILOS", "TOTAL"});
        reportesComprasVentas(modelo, fechaMin, fechaMax, tabla, lblCompras, 
                lblTotal, lblGastos);
        miTabla.setModel(modelo);
    }

    private void reportesComprasVentas(DefaultTableModel modelo, Date fechaMin,
            Date fechaMax, String tabla, JLabel lblCompras, JLabel lblTotal, 
            JLabel lblGastos) throws SQLException {
        long total = 0;
        long gastos = 0;
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = consulta(tabla, fechaMin, fechaMax);
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        while (res.next()) {
            modelo.addRow(new Object[]{res.getString("nombre"), formateo.format(res.getDouble("Kilos")),
                formateo.format(res.getLong("Total"))});
            if (res.getString("nombre").equalsIgnoreCase("gastos")) {
                gastos += res.getLong("Total");
            } else {
                total += res.getLong("Total");
            }
        }
        lblCompras.setText(formateo.format(total));
        lblGastos.setText(formateo.format(gastos));
        lblTotal.setText(formateo.format((total + gastos)));
        miConexion.close();
    }

    public void reporteDeTodo(JTable miTabla, Date fechaMin, Date fechaMax,
            JLabel lblCompras, JLabel lblGastos, JLabel lblTotal, JLabel lblVenta) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ArrayList<Object> valores = new ArrayList<>();
        modelo.setColumnIdentifiers(new Object[]{"TABLA", "PRODUCTO", "KILOS", "TOTAL"});
        reporteDeTodoCompras(modelo, fechaMin, fechaMax, "compras", valores);
        reporteDeTodoCompras(modelo, fechaMin, fechaMax, "ventas", valores);
        miTabla.setModel(modelo);
        long compras = (long) valores.get(0);
        long gastos = (long) valores.get(1);
        long ventas = (long) valores.get(2);
        long total = ventas-gastos-compras;
        lblCompras.setText(formateo.format(compras));
        lblGastos.setText(formateo.format(gastos));
        lblVenta.setText(formateo.format(ventas));
        lblTotal.setText(formateo.format(total));
    }

    private void reporteDeTodoCompras(DefaultTableModel modelo, Date fechaMin, 
            Date fechaMax, String tabla, ArrayList<Object> obj) throws SQLException {
        long total=0;
        long gastos=0;
        
        Connection miConexion = new ConexionBD().realizarConexion();
        String consulta = consulta(tabla, fechaMin, fechaMax);
        Statement sentencia = miConexion.createStatement();
        ResultSet res = sentencia.executeQuery(consulta);
        while (res.next()) {
            modelo.addRow(new Object[]{tabla, res.getString("nombre"), formateo.format(res.getDouble("Kilos")),
                formateo.format(res.getLong("Total"))});
            if (res.getString("nombre").equalsIgnoreCase("gastos")) {
                gastos += res.getLong("Total");
            } else {
                total += res.getLong("Total");
            }
        }
        obj.add(total);
        obj.add(gastos);
        miConexion.close();
    }

    private String consulta(String tabla, Date fechaMin, Date fechaMax) {
        return "SELECT pro.nombre, SUM(kilos) AS Kilos, SUM(total) "
                + "as Total FROM " + tabla + " JOIN productos as pro "
                + "on pro.id = " + tabla + ".id_productos "
                + "WHERE fecha >= '" + fechaMin + "' AND fecha<= '" + fechaMax + "' "
                + "GROUP BY pro.nombre";
    }

}
