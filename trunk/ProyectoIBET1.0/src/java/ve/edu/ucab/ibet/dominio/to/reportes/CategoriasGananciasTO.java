/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.dominio.to.reportes;

/**
 *
 * @author maya
 */
public class CategoriasGananciasTO {

    private double montoTotal;
    private String nombreCategoria;

    public CategoriasGananciasTO() {
    }
    
    public CategoriasGananciasTO(double montoTotal, String nombreCategoria) {
        this.montoTotal = montoTotal;
        this.nombreCategoria = nombreCategoria;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }



}
