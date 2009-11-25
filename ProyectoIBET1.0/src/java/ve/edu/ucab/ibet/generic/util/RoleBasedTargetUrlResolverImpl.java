package ve.edu.ucab.ibet.generic.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.ui.TargetUrlResolver;
import org.springframework.security.ui.savedrequest.SavedRequest;
import ve.edu.ucab.ibet.dominio.enums.Role;

/**
 * Implementacion del target de resolucion de url para enviar
 * a un usuario que es administrador director a la pagina de
 * admnistracion
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RoleBasedTargetUrlResolverImpl implements TargetUrlResolver {

    private static final String ADMIN_ROLE_TARGET_URL = "/privado/back/admin.htm";
    private final TargetUrlResolver defaultSpringSecurityUrlResolver;

    public RoleBasedTargetUrlResolverImpl(
            final TargetUrlResolver fallbackTargetUrlResolver) {
        defaultSpringSecurityUrlResolver = fallbackTargetUrlResolver;
    }

    public String determineTargetUrl(SavedRequest savedRequest, HttpServletRequest currentRequest, Authentication auth) {
        if (containsAdminAuthority(auth)) {
            return ADMIN_ROLE_TARGET_URL;
        }

        return defaultSpringSecurityUrlResolver.determineTargetUrl(
                savedRequest, currentRequest, auth);

    }

    private boolean containsAdminAuthority(final Authentication auth) {
        for (final GrantedAuthority grantedAuthority : auth.getAuthorities()) {
            if (grantedAuthority.getAuthority().equals(
                    Role.ADMIN_ROLE.roleName())) {

                return true;
            }
        }
        return false;
    }
}