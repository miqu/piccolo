package org.piccolo;

/**
 * Controls the authorizations
 */
public class AuthorizationControllerImpl implements AuthorizationController {
    final String KARHU="4742124002473";

    public boolean requestAccess(String id){

        return KARHU.equals(id);
    }
}
