/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.generic.util;

import com.pd.dominio.Role;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.ui.TargetUrlResolver;
import org.springframework.security.ui.savedrequest.SavedRequest;

/**
 *
 * @author nath
 */
public class RoleBasedTargetUrlResolverImpl implements TargetUrlResolver {

    private static final String ADMIN_ROLE_TARGET_URL = "/admin.htm";
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
