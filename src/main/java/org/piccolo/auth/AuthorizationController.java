package org.piccolo.auth;

/**
 * Created by mrt on 24.10.2014.
 */

public interface AuthorizationController {
    boolean requestAccess(String id);
}
