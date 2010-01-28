package ve.edu.ucab.ibet.dominio.to.reportes;

/**
 * Transfer object para manejar las perdidas por categorias 
 * @author maya
 */
public class CategoriasPerdidasTO {

    private Integer id;
    private Double monto;
    private String nombreCategoria;
    private Boolean ganoApuesta;
    private Boolean empatoApuesta;
    private Boolean ganoTableroGananacia;
    private Boolean empatoTableroGanancia;
    private Double proporcionGano;
    private Double proporcionEmpato;

    public CategoriasPerdidasTO() {
    }

    public CategoriasPerdidasTO(Integer id, Double monto, String nombreCategoria, Boolean ganoApuesta, Boolean empatoApuesta, Boolean ganoTableroGananacia, Boolean empatoTableroGanancia, Double proporcionGano, Double proporcionEmpato) {
        this.id = id;
        this.monto = monto;
        this.nombreCategoria = nombreCategoria;
        this.ganoApuesta = ganoApuesta;
        this.empatoApuesta = empatoApuesta;
        this.ganoTableroGananacia = ganoTableroGananacia;
        this.empatoTableroGanancia = empatoTableroGanancia;
        this.proporcionGano = proporcionGano;
        this.proporcionEmpato = proporcionEmpato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Boolean getEmpatoApuesta() {
        return empatoApuesta;
    }

    public void setEmpatoApuesta(Boolean empatoApuesta) {
        this.empatoApuesta = empatoApuesta;
    }

    public Boolean getEmpatoTableroGanancia() {
        return empatoTableroGanancia;
    }

    public void setEmpatoTableroGanancia(Boolean empatoTableroGanancia) {
        this.empatoTableroGanancia = empatoTableroGanancia;
    }

    public Boolean getGanoApuesta() {
        return ganoApuesta;
    }

    public void setGanoApuesta(Boolean ganoApuesta) {
        this.ganoApuesta = ganoApuesta;
    }

    public Boolean getGanoTableroGananacia() {
        return ganoTableroGananacia;
    }

    public void setGanoTableroGananacia(Boolean ganoTableroGananacia) {
        this.ganoTableroGananacia = ganoTableroGananacia;
    }

    public Double getProporcionEmpato() {
        return proporcionEmpato;
    }

    public void setProporcionEmpato(Double proporcionEmpato) {
        this.proporcionEmpato = proporcionEmpato;
    }

    public Double getProporcionGano() {
        return proporcionGano;
    }

    public void setProporcionGano(Double proporcionGano) {
        this.proporcionGano = proporcionGano;
    }
    
}
