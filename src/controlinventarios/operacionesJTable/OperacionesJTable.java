/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.operacionesJTable;

import controlinventarios.objects.Compras;
import controlinventarios.objects.Ventas;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanj
 */
public class OperacionesJTable {
    
    DecimalFormat formateo = new DecimalFormat("###,###.##");
    
    public void llenarTablaCompras(JTable miTabla, ArrayList<Compras> listaCompra){
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.setColumnIdentifiers(new Object[]{"CLIENTE","PRODUCTO",
        "PRECIO","KILOS"});
        for(int i = 0; i<listaCompra.size(); i++){
            Object[] datos = new Object[5];
            datos[0] = listaCompra.get(i).getProveedor();
            datos[1] = listaCompra.get(i).getProducto();
            datos[2] = formateo.format(listaCompra.get(i).getPrecio());
            datos[3] = formateo.format(listaCompra.get(i).getKilos());
            modelo.addRow(datos);
        }
        
        miTabla.setModel(modelo);
    }
    
    public void llenarTablaVentas(JTable miTabla, ArrayList<Ventas> listaVenta){
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.setColumnIdentifiers(new Object[]{"PROVEEDOR","PRODUCTO",
        "PRECIO","KILOS"});
        for(int i = 0; i<listaVenta.size(); i++){
            Object[] datos = new Object[5];
            datos[0] = listaVenta.get(i).getProveedor();
            datos[1] = listaVenta.get(i).getProducto();
            datos[2] = formateo.format(listaVenta.get(i).getPrecio());
            datos[3] = formateo.format(listaVenta.get(i).getKilos());
            modelo.addRow(datos);
        }
        
        miTabla.setModel(modelo);
    }
    
    public void agregarCompra(ArrayList listaCompra, String fecha, 
            String proveedor, String producto, int precio, double kilos){
        Compras compra = new Compras(fecha, proveedor, producto, precio, kilos);
        listaCompra.add(compra);
    }
    
    public void agregarVenta(ArrayList listaCompra, String fecha, 
            String proveedor, String producto, int precio, double kilos){
        Ventas venta = new Ventas(fecha, proveedor, producto, precio, kilos);
        listaCompra.add(venta);
    }
    
    public void eliminarRegistroTabla(int index, ArrayList<Compras> listaCompra){
        listaCompra.remove(index);
    }
    
}
