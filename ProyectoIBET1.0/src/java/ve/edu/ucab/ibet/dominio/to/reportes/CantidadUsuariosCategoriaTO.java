package ve.edu.ucab.ibet.dominio.to.reportes;

/**
 *
 * @author maya
 */
public class CantidadUsuariosCategoriaTO {

    private Long cantidadUsuarios;
    private String nombreCategoria;

    public CantidadUsuariosCategoriaTO() {
    }

    public CantidadUsuariosCategoriaTO(Long cantidadUsuarios, String nombreCategoria) {
        this.cantidadUsuarios = cantidadUsuarios;
        this.nombreCategoria = nombreCategoria;
    }

    public Long getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(Long cantidadUsuarios) {
        this.cantidadUsuarios = cantidadUsuarios;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }


}
