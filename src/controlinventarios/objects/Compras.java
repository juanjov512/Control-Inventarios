/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlinventarios.objects;

/**
 *
 * @author juanj
 */
public class Compras {
    private String fecha;
    private String proveedor;
    private String producto;
    private int precio;
    private double kilos;
    
    public Compras(String fecha, String proveedor, String producto, int precio,
            double kilos){
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.producto = producto;
        this.precio = precio;
        this.kilos = kilos;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the proveedor
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * @return the kilos
     */
    public double getKilos() {
        return kilos;
    }

    /**
     * @param kilos the kilos to set
     */
    public void setKilos(double kilos) {
        this.kilos = kilos;
    }
    
     
    
}
