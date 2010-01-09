package ve.edu.ucab.ibet.dominio.enums;

/**
 * Enumerado para el manejo de roles de la aplicacion
 * @author Gerardo Barcia
 * @version 1.0
 */
public enum Role {

    ADMIN_ROLE("ROLE_ADMIN", 1), USER_ROLE("ROLE_USER", 0);
    private final int order;
    private final String roleName;

    private Role(final String roleName, final int order) {
        this.roleName = roleName;
        this.order = order;
    }

    public int order() {
        return order;
    }

    public String roleName() {
        return roleName;
    }
}
