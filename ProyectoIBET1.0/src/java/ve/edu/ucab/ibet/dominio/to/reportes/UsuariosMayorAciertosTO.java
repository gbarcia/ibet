package ve.edu.ucab.ibet.dominio.to.reportes;

/**
 *
 * @author maya
 */
public class UsuariosMayorAciertosTO {

    private Long cantidadAciertos;
    private String username;

    public UsuariosMayorAciertosTO() {
    }

    public UsuariosMayorAciertosTO(Long cantidadAciertos, String username) {
        this.cantidadAciertos = cantidadAciertos;
        this.username = username;
    }

    public Long getCantidadAciertos() {
        return cantidadAciertos;
    }

    public void setCantidadAciertos(Long cantidadAciertos) {
        this.cantidadAciertos = cantidadAciertos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
