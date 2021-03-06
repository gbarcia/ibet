package ve.edu.ucab.ibet.dominio.to.reportes;

/**
 * Transfer object para manejar las ganancias y perdidas por categoria 
 * @author maya
 */
public class CategoriasGananciaPerdidaTO {

    private Double montoTotal;
    private String nombreCategoria;

    public CategoriasGananciaPerdidaTO() {
    }
    
    public CategoriasGananciaPerdidaTO(Double montoTotal, String nombreCategoria) {
        this.montoTotal = montoTotal;
        this.nombreCategoria = nombreCategoria;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }



}
